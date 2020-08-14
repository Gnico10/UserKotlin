package ViewModels

import Interface.IonClick
import Library.Networks
import Library.Validate
import Models.BindableString
import android.app.Activity
import android.content.Intent
import android.net.Network
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.bigbrains.userskotlin.MainActivity
import com.bigbrains.userskotlin.R
import com.bigbrains.userskotlin.VerificarPassword
import com.bigbrains.userskotlin.databinding.VerifyEmailBinding
import com.bigbrains.userskotlin.databinding.VerifyPasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.verify_email.view.*

class LoginViewModels(activity: Activity,
                      bindingEmail: VerifyEmailBinding?,
                      bindingPasword: VerifyPasswordBinding?) : ViewModel(), IonClick{

    private var _activity: Activity? = null
    var emailUI = BindableString()
    var passwordUI = BindableString()
    var email: String? = null

    private var mAuth: FirebaseAuth? = null

    companion object{
        private var _bindingEmail: VerifyEmailBinding? = null
        private var emailData: String? = null

        private var _bindingPassword: VerifyPasswordBinding? = null
        private var passwordData: String? = null

    }

    init{
        _activity = activity
        _bindingEmail = bindingEmail
        _bindingPassword = bindingPasword


        if(emailData != null){
            // Para el caso de que vuelva a la actividad para cargar correo
            emailUI.setValue(emailData!!)
            email = emailData
        }

        mAuth = FirebaseAuth.getInstance()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View) {
        when(view.id){
            R.id.email_sign_in_button -> verificarEmail()
            R.id.password_sign_in_button -> login()
        }
        //if(emailUI.getValue()!= "") Toast.makeText(_activity, emailUI.getValue(), Toast.LENGTH_SHORT).show()
    }

    private fun verificarEmail(){
        var cancel = true

        if(TextUtils.isEmpty(emailUI.getValue())){
            _bindingEmail!!.emailEditText.error = _activity!!.getString(R.string.error_field_required)
            _bindingEmail!!.emailEditText.requestFocus()
            cancel = false
        }else if (!Validate.isEmail(emailUI.getValue())){
            _bindingEmail!!.emailEditText.error = _activity!!.getString(R.string.error_invalid_email)
            cancel = false
        }

        if(cancel){
            emailData = emailUI.getValue()
            _activity!!.startActivity(Intent(_activity,  VerificarPassword::class.java))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun login(){
        var cancel = true

        if(TextUtils.isEmpty(passwordUI.getValue())){
            _bindingPassword!!.passwordEditText.error = _activity!!.getString(R.string.error_field_required)
            _bindingPassword!!.passwordEditText.requestFocus()
            cancel = false
        }else if (!Validate.isPassword(passwordUI.getValue())){
            _bindingPassword!!.passwordEditText.error = _activity!!.getString(R.string.error_invalid_password)
            cancel = false
        }

        if(cancel && Networks(_activity!!).verificarNetworks()){
            passwordData = passwordUI.getValue()
            mAuth!!.signInWithEmailAndPassword(emailData!!, passwordData!!)
                .addOnCompleteListener(_activity!!){
                task -> if(task.isSuccessful){
                    val intent = Intent(_activity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    _activity!!.startActivity(intent)

                }else{
                    Snackbar.make(_bindingPassword!!.passwordEditText, R.string.invalid_credentials, Snackbar.LENGTH_LONG).show()
                }
            }

        }else{
            Snackbar.make(_bindingPassword!!.passwordEditText, R.string.networks, Snackbar.LENGTH_LONG).show()
        }
    }
}