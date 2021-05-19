package com.earthx.sentimenter.view.authentication.signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.earthx.sentimenter.data.source.local.sp.SharedPreferences
import com.earthx.sentimenter.databinding.ActivitySignupBinding
import com.earthx.sentimenter.view.authentication.signin.SigninActivity
import com.earthx.sentimenter.view.home.HomeActivity
import com.earthx.sentimenter.view.authentication.viewmodel.ViewModelFactory
import com.earthx.sentimenter.view.onboarding.OnboardingActivity
import com.earthx.sentimenter.vo.Status

class SignupActivity : AppCompatActivity() {
    private lateinit var onSignupBinding: ActivitySignupBinding
    private lateinit var viewModel : SignupViewModel
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreference =  this.getSharedPreferences(
            SharedPreferences.loggedUser,
            Context.MODE_PRIVATE)
        token = sharedPreference.getString("token","").toString()
        if(token!=""){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        else{
            super.onCreate(savedInstanceState)
            onSignupBinding = ActivitySignupBinding.inflate(layoutInflater)
            setContentView(onSignupBinding.root)

            val factory = ViewModelFactory.getInstance(this)
            viewModel = ViewModelProvider(this, factory)[SignupViewModel::class.java]


            onSignupBinding.progressBar.visibility = View.GONE
            onSignupBinding.backButton.setOnClickListener {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
            onSignupBinding.buttonSignup.setOnClickListener {
                handleSignup()
                startActivity(Intent(this, HomeActivity::class.java))
            }

            onSignupBinding.textToLogin.setOnClickListener {
                startActivity(Intent(this, SigninActivity::class.java))
                finish()

            }

        }

    }

    private fun handleSignup(){
        val email = onSignupBinding.emailTextField.editText?.text.toString()
        val password = onSignupBinding.passwordTextField.editText?.text.toString()
        viewModel.signup(email, password).observe(this, Observer{
                data->
            if(data !=null){
                when(data.status){
                    Status.LOADING -> {
                        onSignupBinding.progressBar.visibility = View.VISIBLE
                        onSignupBinding.buttonSignup.visibility = View.GONE
                        Toast.makeText(applicationContext, "loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        onSignupBinding.progressBar.visibility = View.GONE
                        onSignupBinding.buttonSignup.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, "Signup success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, SigninActivity::class.java))
                        finish()
                    }

                    Status.ERROR->{
                        onSignupBinding.progressBar.visibility = View.GONE
                        onSignupBinding.buttonSignup.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, data.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }



        })
    }
}