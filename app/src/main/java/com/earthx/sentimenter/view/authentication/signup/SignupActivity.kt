package com.earthx.sentimenter.view.authentication.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.earthx.sentimenter.databinding.ActivitySignupBinding
import com.earthx.sentimenter.view.authentication.signin.SigninActivity
import com.earthx.sentimenter.view.home.HomeActivity
import com.earthx.sentimenter.view.onboarding.OnboardingActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var onSignupBinding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSignupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(onSignupBinding.root)
        onSignupBinding.backButton.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }
        onSignupBinding.buttonSignup.setOnClickListener {
            handleSignup()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun handleSignup(){
        Toast.makeText(applicationContext, "Berhasil sign up", Toast.LENGTH_SHORT).show()
    }
}