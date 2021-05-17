package com.earthx.sentimenter.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.earthx.sentimenter.databinding.ActivityHomeBinding
import com.earthx.sentimenter.view.onboarding.OnboardingActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var onHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(onHomeBinding.root)
        onHomeBinding.graph.setOnClickListener {
            handleGraph()
        }
        onHomeBinding.sentimentAnalysis.setOnClickListener {
            handleSentiment()
        }
        onHomeBinding.logout.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
    }

    private fun handleGraph(){
        Toast.makeText(applicationContext, "Membuka fitur graph", Toast.LENGTH_SHORT).show()
    }

    private fun handleSentiment(){
        Toast.makeText(applicationContext, "Membuka fitur sentiment analysis", Toast.LENGTH_SHORT).show()
    }
}