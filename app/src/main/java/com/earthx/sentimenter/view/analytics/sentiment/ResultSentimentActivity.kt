package com.earthx.sentimenter.view.analytics.sentiment

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.model.ResultGraphItem
import com.earthx.sentimenter.data.model.ResultSentiment
import com.earthx.sentimenter.databinding.ActivityResultSentimentBinding
import com.earthx.sentimenter.view.home.HomeActivity
import com.github.mikephil.charting.data.*

class ResultSentimentActivity : AppCompatActivity() {

    private lateinit var _onResultSentimentBinding: ActivityResultSentimentBinding
    private lateinit var listResult: ResultSentiment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _onResultSentimentBinding = ActivityResultSentimentBinding.inflate(layoutInflater)
        setContentView(_onResultSentimentBinding.root)
        _onResultSentimentBinding.backButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        listResult = intent.getParcelableExtra<ResultSentiment>("EXTRA_RESULT") as ResultSentiment

        setPieChart()
    }

    fun setPieChart() {
        _onResultSentimentBinding.resultPercentage.text =String.format("%.2f", listResult.percentage) +"%"
        _onResultSentimentBinding.resultValue.text = listResult.sentiment
        _onResultSentimentBinding.resultText.text = "'" + listResult.name + "'"
        _onResultSentimentBinding.resultTotalTweet.text = "Total tweets: "+ listResult.total_tweet.toString()

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(0.7f, "Positive"))
        entries.add(PieEntry(0.3f, "Negative"))

        val pieDataSet = PieDataSet(entries, "DATA")
        pieDataSet.setColors(listOf(Color.LTGRAY, Color.LTGRAY))
        pieDataSet.setValueTextColor(Color.BLACK)
        pieDataSet.setValueTextSize(16f)

        val pieData = PieData(pieDataSet)
    }
}