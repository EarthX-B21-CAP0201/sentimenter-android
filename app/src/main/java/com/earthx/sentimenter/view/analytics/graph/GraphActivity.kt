package com.earthx.sentimenter.view.analytics.graph

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.source.local.sp.SharedPreferences
import com.earthx.sentimenter.databinding.ActivityGraphBinding
import com.earthx.sentimenter.view.analytics.viewmodel.ViewModelFactory
import com.earthx.sentimenter.view.status.FailedActivity
import com.earthx.sentimenter.vo.Status
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date.from


class GraphActivity : AppCompatActivity() {
    private lateinit var onGraphActivityBinding: ActivityGraphBinding
    private lateinit var viewModel : GraphViewModel
    private lateinit var email : String
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreference =  this.getSharedPreferences(
            SharedPreferences.loggedUser,
            Context.MODE_PRIVATE)

        email = sharedPreference.getString("email","").toString()
        token = sharedPreference.getString("token","").toString()

        onGraphActivityBinding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(onGraphActivityBinding.root)
        setDropdownMenus()
        setDatePicker()
        onGraphActivityBinding.progressBar.visibility = View.GONE
        onGraphActivityBinding.buttonGenerate.setOnClickListener {
            generateGraph()
        }

        onGraphActivityBinding.backButton.setOnClickListener {
            onBackPressed()
        }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[GraphViewModel::class.java]

    }
    private fun setDropdownMenus(){
        val itemsCategory = listOf("Category 1", "Category 2", "Category 3", "Category 4")
        val adapterCategory = ArrayAdapter(this, R.layout.list_item, itemsCategory)
        onGraphActivityBinding.menuCategoryAutoComplete.setAdapter(adapterCategory)

        val itemsLanguage = listOf("Indonesia", "English")
        val adapterLanguage =  ArrayAdapter(this, R.layout.list_item, itemsLanguage)
      onGraphActivityBinding.menuLanguageAutoComplete.setAdapter(adapterLanguage)
    }

    private fun setDatePicker(){
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now())
        val pickerStart =
            MaterialDatePicker.Builder.datePicker()
                .setSelection(today)
                .setCalendarConstraints(constraintsBuilder.build())
                .setTitleText("Select start date")

                .build()
        val pickerEnd =
            MaterialDatePicker.Builder.datePicker()
                .setSelection(today)
                .setCalendarConstraints(constraintsBuilder.build())
                .setTitleText("Select end date")

                .build()

        onGraphActivityBinding.pickDatestartButton.setOnClickListener {
            pickerStart.show(supportFragmentManager, "tag")
        }
        pickerStart.addOnPositiveButtonClickListener {
            onGraphActivityBinding.textDateStart.text = pickerStart.headerText
        }

        onGraphActivityBinding.pickDateendButton.setOnClickListener {
            pickerEnd.show(supportFragmentManager, "tag")
        }
        pickerEnd.addOnPositiveButtonClickListener {
            onGraphActivityBinding.textDateEnd.text = pickerEnd.headerText
        }



    }

    private fun generateGraph(){
        val keyword = onGraphActivityBinding.keywordTextField.editText?.text.toString()
        val hashtag = onGraphActivityBinding.hashtagTextField.editText?.text.toString()
        val category = onGraphActivityBinding.menuCategory.editText?.text.toString()
        val language = onGraphActivityBinding.menuLanguage.editText?.text.toString()
        val isRetweeted = onGraphActivityBinding.isRetweeted.isChecked
        val isRealtime = onGraphActivityBinding.isRealtime.isChecked

        val dateStart = onGraphActivityBinding.textDateStart.text.toString()
        val dateEnd = onGraphActivityBinding.textDateEnd.text.toString()
        viewModel.generateTop10(token,keyword,
            hashtag,category,language,isRetweeted,isRealtime,dateStart,dateEnd).observe(this, Observer{
                data->
            if(data !=null){
                when(data.status){
                    Status.LOADING -> {
                        onGraphActivityBinding.progressBar.visibility = View.VISIBLE
                        onGraphActivityBinding.svForm.visibility = View.GONE
                        Toast.makeText(applicationContext, "Generating...", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        onGraphActivityBinding.progressBar.visibility = View.GONE
                        onGraphActivityBinding.svForm.visibility = View.VISIBLE
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