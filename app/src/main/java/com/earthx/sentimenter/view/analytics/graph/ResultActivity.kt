package com.earthx.sentimenter.view.analytics.graph

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.earthx.sentimenter.data.model.ResultGraphItem
import com.earthx.sentimenter.data.source.remote.response.GenerateGraphResponse
import com.earthx.sentimenter.databinding.ActivityResultBinding
import com.earthx.sentimenter.view.analytics.graph.adapter.ResultListAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter


class ResultActivity : AppCompatActivity() {
    private lateinit var onResultActivityBinding: ActivityResultBinding
    private var listResult : ArrayList<ResultGraphItem> = ArrayList<ResultGraphItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onResultActivityBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(onResultActivityBinding.root)
        onResultActivityBinding.backButton.setOnClickListener {
            onBackPressed()
        }
        listResult = intent.getParcelableArrayListExtra<ResultGraphItem>("EXTRA_RESULT") as ArrayList<ResultGraphItem>
        showChart()
        showRecyclerList()


    }

    private fun showChart(){
        var barChart : BarChart = onResultActivityBinding.barChart;
        var list = ArrayList<BarEntry>()
       for (i in 0 until listResult.size){
           list.add(BarEntry(i+1.toFloat(), listResult[i].count.toFloat()))
       }


        var barDataSet = BarDataSet(list, "DATA")
        barDataSet.setColors(listOf(Color.LTGRAY,Color.LTGRAY,Color.LTGRAY,Color.LTGRAY,Color.LTGRAY))
        barDataSet.setValueTextColor(Color.BLACK)
        barDataSet.setValueTextSize(16f)

        var barData = BarData(barDataSet)
        barChart.setFitBars(true);
        barChart.data = barData
        barChart.description.text = "Top 10 Graph"
        barChart.animateY(2000)

        var labels = ArrayList<String>()
        for (i in 0 until listResult.size){
            labels.add(listResult[i].name)
        }
        val xAxis: XAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE

        val formatter: ValueFormatter =
            object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return labels.get(value.toInt()-1)
                }
            }

        xAxis.granularity = 1f // minimum axis-step (interval) is 1

        xAxis.valueFormatter = formatter

    }

    private fun showRecyclerList() {

        onResultActivityBinding.rvGraph.layoutManager = LinearLayoutManager(this)
        val listResultAdapter = ResultListAdapter(listResult, this)
        onResultActivityBinding.rvGraph.adapter = listResultAdapter

    }
}