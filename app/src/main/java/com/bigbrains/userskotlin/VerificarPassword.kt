package com.bigbrains.userskotlin

import ViewModels.LoginViewModels
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bigbrains.userskotlin.databinding.VerifyEmailBinding
import com.bigbrains.userskotlin.databinding.VerifyPasswordBinding

class VerificarPassword: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.verify_password)

        var binding = DataBindingUtil.setContentView<VerifyPasswordBinding>(this, R.layout.verify_password)
        binding.passwordModel = LoginViewModels(this, null, binding)


        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorBlue)

        fun onBackPressed() {
            super.onBackPressed()
            val intent = Intent(this, VerifyEmail::class.java)
            //Limpiar tarea actual y crear una nueva.
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}