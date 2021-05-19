package com.earthx.sentimenter.view.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.source.local.sp.SharedPreferences
import com.earthx.sentimenter.view.home.HomeActivity
import com.earthx.sentimenter.view.onboarding.OnboardingActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var token :String
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreference =  this.getSharedPreferences(
            SharedPreferences.loggedUser,
            Context.MODE_PRIVATE)
        token = sharedPreference.getString("token","").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            if(token!==""){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }

        }, SPLASH_TIME_OUT)
    }
}