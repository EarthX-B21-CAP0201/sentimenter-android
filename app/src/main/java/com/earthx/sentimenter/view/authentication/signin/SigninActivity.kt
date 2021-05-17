package com.earthx.sentimenter.view.authentication.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.earthx.sentimenter.databinding.ActivitySigninBinding
import com.earthx.sentimenter.view.home.HomeActivity
import com.earthx.sentimenter.view.onboarding.OnboardingActivity

class SigninActivity : AppCompatActivity() {
    private lateinit var onSigninBinding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSigninBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(onSigninBinding.root)
        onSigninBinding.backButton.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }
        onSigninBinding.buttonLogin.setOnClickListener {
            handleLogin()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun handleLogin(){
        Toast.makeText(applicationContext, "Berhasil login", Toast.LENGTH_SHORT).show()
    }
}