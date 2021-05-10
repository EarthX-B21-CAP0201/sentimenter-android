package com.earthx.sentimenter.view.authentication.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earthx.sentimenter.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {
    private lateinit var onSigninBinding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSigninBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(onSigninBinding.root)
        onSigninBinding.backButton.setOnClickListener {
            onBackPressed()
        }

    }
}