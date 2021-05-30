package com.earthx.sentimenter.view.analytics.sentiment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earthx.sentimenter.R
import com.github.mikephil.charting.data.*

class ResultSentimentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_sentiment)
    }

    fun setPieChart() {
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