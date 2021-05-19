package com.earthx.sentimenter.view.authentication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.earthx.sentimenter.data.repository.AuthRepository

import com.earthx.sentimenter.di.Injection
import com.earthx.sentimenter.view.authentication.signin.SigninViewModel
import com.earthx.sentimenter.view.authentication.signup.SignupViewModel

class ViewModelFactory private constructor(private val mAuthRepository: AuthRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideAuthRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(SigninViewModel::class.java) -> {
                return SigninViewModel(mAuthRepository) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                return SignupViewModel(mAuthRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}