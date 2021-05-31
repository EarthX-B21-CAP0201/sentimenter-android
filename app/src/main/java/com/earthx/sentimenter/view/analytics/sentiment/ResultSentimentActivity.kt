package com.earthx.sentimenter.view.analytics.sentiment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.model.ResultGraphItem
import com.earthx.sentimenter.data.model.ResultSentiment
import com.earthx.sentimenter.databinding.ActivityResultSentimentBinding
import com.github.mikephil.charting.data.*

class ResultSentimentActivity : AppCompatActivity() {

    private lateinit var _onResultSentimentBinding: ActivityResultSentimentBinding
    private lateinit var listResult: ResultSentiment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _onResultSentimentBinding = ActivityResultSentimentBinding.inflate(layoutInflater)
        setContentView(_onResultSentimentBinding.root)
        _onResultSentimentBinding.backButton.setOnClickListener {
            onBackPressed()
        }
        listResult = intent.getParcelableExtra<ResultSentiment>("EXTRA_RESULT") as ResultSentiment
        setPieChart()
    }

    fun setPieChart() {
        _onResultSentimentBinding.resultPercentage.text = listResult.percentage.toString() + "%"
        _onResultSentimentBinding.resultValue.text = listResult.sentiment
        _onResultSentimentBinding.resultText.text = "'" + listResult.name + "'"

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