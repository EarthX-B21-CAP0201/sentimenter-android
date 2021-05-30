package com.earthx.sentimenter.view.analytics.sentiment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.earthx.sentimenter.R
import com.earthx.sentimenter.databinding.ActivitySentimentBinding
import com.earthx.sentimenter.view.analytics.graph.GraphViewModel
import com.earthx.sentimenter.view.analytics.graph.ResultActivity
import com.earthx.sentimenter.view.status.FailedActivity
import com.earthx.sentimenter.vo.Status

class SentimentActivity : AppCompatActivity() {

    private lateinit var _onGraphSentimentBinding: ActivitySentimentBinding
    private lateinit var viewModel : SentimentViewModel
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentiment)
        _onGraphSentimentBinding.buttonGenerate.setOnClickListener {
            generateSentiment()
        }
    }

    private fun generateSentiment() {
        val keyword = _onGraphSentimentBinding.keywordTextField.editText?.text.toString()

        viewModel.generateSentiment(token,keyword).observe(this, Observer{
                data->
            if(data !=null){
                when(data.status){
                    Status.LOADING -> {
                        _onGraphSentimentBinding.progressBar.visibility = View.VISIBLE
                        _onGraphSentimentBinding.svForm.visibility = View.GONE
                        Toast.makeText(applicationContext, "Generating...", Toast.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS -> {
                        _onGraphSentimentBinding.progressBar.visibility = View.GONE
                        _onGraphSentimentBinding.svForm.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, "Generate success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("EXTRA_RESULT", data.data?.result)
                        startActivity(intent)
                    }

                    Status.ERROR->{
                        Toast.makeText(applicationContext, data.message, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, FailedActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}