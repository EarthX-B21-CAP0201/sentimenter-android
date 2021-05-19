package com.earthx.sentimenter.view.authentication.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.earthx.sentimenter.databinding.ActivitySigninBinding
import com.earthx.sentimenter.view.authentication.signup.SignupActivity
import com.earthx.sentimenter.view.authentication.viewmodel.ViewModelFactory
import com.earthx.sentimenter.view.onboarding.OnboardingActivity
import com.earthx.sentimenter.vo.Status

class SigninActivity : AppCompatActivity() {
    private lateinit var onSigninBinding: ActivitySigninBinding
    private lateinit var viewModel : SigninViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSigninBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(onSigninBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[SigninViewModel::class.java]


        onSigninBinding.progressBar.visibility = View.GONE
        onSigninBinding.backButton.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }
        onSigninBinding.buttonLogin.setOnClickListener {
            handleLogin()
        }
        onSigninBinding.textToSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()

        }
    }

    private fun handleLogin(){

        val email = onSigninBinding.emailTextField.editText?.text.toString()
        val password = onSigninBinding.passwordTextField.editText?.text.toString()
        viewModel.signin(email, password).observe(this, Observer{
            user->
            if(user !=null){
                when(user.status){
                    Status.LOADING -> {

                        onSigninBinding.progressBar.visibility = View.VISIBLE
                        onSigninBinding.buttonLogin.visibility = View.GONE
                        Toast.makeText(applicationContext, "loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        onSigninBinding.progressBar.visibility = View.GONE
                        onSigninBinding.buttonLogin.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, "Signin success", Toast.LENGTH_SHORT).show()
                    }

                    Status.ERROR->{
                        onSigninBinding.progressBar.visibility = View.GONE
                        onSigninBinding.buttonLogin.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, user.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }



        })
    }
}