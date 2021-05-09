package com.earthx.sentimenter.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.earthx.sentimenter.R
import com.earthx.sentimenter.view.onboarding.OnboardingActivity


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}