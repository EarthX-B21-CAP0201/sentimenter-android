package com.earthx.sentimenter.view.status

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earthx.sentimenter.databinding.ActivityFailedBinding

class FailedActivity : AppCompatActivity() {
    private lateinit var onFailedActivityBinding: ActivityFailedBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        onFailedActivityBinding = ActivityFailedBinding.inflate(layoutInflater)
        setContentView(onFailedActivityBinding.root)
        super.onCreate(savedInstanceState)
        onFailedActivityBinding.buttonFailed.setOnClickListener {
            onBackPressed()
        }
    }
}