package com.earthx.sentimenter.view.status

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earthx.sentimenter.databinding.ActivityFailedBinding
import com.earthx.sentimenter.view.home.HomeActivity

class FailedActivity : AppCompatActivity() {
    private lateinit var onFailedActivityBinding: ActivityFailedBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        onFailedActivityBinding = ActivityFailedBinding.inflate(layoutInflater)
        setContentView(onFailedActivityBinding.root)
        super.onCreate(savedInstanceState)
        onFailedActivityBinding.buttonFailed.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}