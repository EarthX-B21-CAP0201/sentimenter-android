package com.earthx.sentimenter.view.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.earthx.sentimenter.data.model.LastActivityItem
import com.earthx.sentimenter.data.source.local.sp.SharedPreferences
import com.earthx.sentimenter.databinding.ActivityHomeBinding
import com.earthx.sentimenter.view.analytics.graph.GraphActivity
import com.earthx.sentimenter.view.analytics.sentiment.SentimentActivity
import com.earthx.sentimenter.view.home.adapter.LastActivityAdapter
import com.earthx.sentimenter.view.home.viewmodel.ViewModelFactory
import com.earthx.sentimenter.view.onboarding.OnboardingActivity
import com.earthx.sentimenter.vo.Status
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {

    private lateinit var onHomeBinding: ActivityHomeBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var email : String
    private lateinit var token: String
    private lateinit var listResult : ArrayList<LastActivityItem>

    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPreference =  this.getSharedPreferences(
            SharedPreferences.loggedUser,
            Context.MODE_PRIVATE)

        email = sharedPreference.getString("email","").toString()
        token = sharedPreference.getString("token","").toString()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        super.onCreate(savedInstanceState)

        onHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(onHomeBinding.root)

        onHomeBinding.textName.text = email
//        onHomeBinding.graph.setOnClickListener {
//            handleGraph()
//        }
        onHomeBinding.sentimentAnalysis.setOnClickListener {
            handleSentiment()
        }
        onHomeBinding.logout.setOnClickListener {
            handleLogout()
        }
        showLastActivity()
    }

    private fun handleGraph(){
        startActivity(Intent(this, GraphActivity::class.java))
    }

    private fun handleSentiment(){
        startActivity(Intent(this, SentimentActivity::class.java))
    }

    private fun showLastActivity() {
        viewModel.lastActivity(token).observe(this, Observer { data ->
            if(data != null) {
                when(data.status) {
                    Status.LOADING -> {
                        onHomeBinding.progressBar.visibility = View.VISIBLE
                        onHomeBinding.rvActivity.visibility = View.GONE
                        Toast.makeText(applicationContext, "loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        onHomeBinding.progressBar.visibility = View.GONE
                        onHomeBinding.rvActivity.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, "Load success", Toast.LENGTH_SHORT).show()
                        onHomeBinding.rvActivity.layoutManager = LinearLayoutManager(this)
                        listResult = data.data?.result ?: ArrayList()
                        listResult.reverse()
                        val listResultAdapter = LastActivityAdapter(listResult, this)
                        onHomeBinding.rvActivity.adapter = listResultAdapter
                    }

                    Status.ERROR->{
                        Toast.makeText(applicationContext, data.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun handleLogout(){
        viewModel.signout(token).observe(this, Observer{
                data->
            if(data !=null){
                when(data.status){
                    Status.LOADING -> {
                        Toast.makeText(applicationContext, "loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        Toast.makeText(applicationContext, "Signout success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, OnboardingActivity::class.java))
                        finish()
                    }

                    Status.ERROR->{
                        Toast.makeText(applicationContext, data.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}