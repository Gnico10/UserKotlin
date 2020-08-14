package com.bigbrains.userskotlin

import ViewModels.LoginViewModels
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bigbrains.userskotlin.databinding.VerifyEmailBinding

class VerifyEmail : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.verify_email)

        var binding = DataBindingUtil.setContentView<VerifyEmailBinding>(this, R.layout.verify_email)
        binding.emailModel = LoginViewModels(this, binding, null)


        val window = this.window
        // Limpiar barra de estado por proporcionar nuevo estilo.
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //Permite tomar la barra de estado y cambiarle el color.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //Cambia el color de la barra de estado.
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorBlue)
    }
}