package com.earthx.sentimenter.view.analytics.sentiment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.source.local.sp.SharedPreferences
import com.earthx.sentimenter.databinding.ActivitySentimentBinding
import com.earthx.sentimenter.view.analytics.graph.GraphViewModel
import com.earthx.sentimenter.view.analytics.graph.ResultActivity
import com.earthx.sentimenter.view.analytics.viewmodel.ViewModelFactory
import com.earthx.sentimenter.view.status.FailedActivity
import com.earthx.sentimenter.vo.Status

class SentimentActivity : AppCompatActivity() {

    private lateinit var _onGraphSentimentBinding: ActivitySentimentBinding
    private lateinit var viewModel : SentimentViewModel
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreference =  this.getSharedPreferences(
            SharedPreferences.loggedUser,
            Context.MODE_PRIVATE)

        token = sharedPreference.getString("token","").toString()

        _onGraphSentimentBinding = ActivitySentimentBinding.inflate(layoutInflater)
        setContentView(_onGraphSentimentBinding.root)
        _onGraphSentimentBinding.buttonGenerate.setOnClickListener {
            generateSentiment()
        }
        _onGraphSentimentBinding.backButton.setOnClickListener {
            onBackPressed()
        }
        _onGraphSentimentBinding.progressBar.visibility = View.GONE
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[SentimentViewModel::class.java]
        setDropdownMenus()
    }

    private fun setDropdownMenus(){
        val itemsLanguage = listOf("ind", "en")
        val adapterLanguage =  ArrayAdapter(this, R.layout.list_item, itemsLanguage)
      _onGraphSentimentBinding.menuLanguageAutoComplete.setAdapter(adapterLanguage)
    }

    private fun generateSentiment() {
        val keyword = _onGraphSentimentBinding.keywordTextField.editText?.text.toString()
        val language = _onGraphSentimentBinding.menuLanguage.editText?.text.toString()

        viewModel.generateSentiment(token,keyword,language).observe(this, Observer{
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
                        val intent = Intent(this, ResultSentimentActivity::class.java)
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