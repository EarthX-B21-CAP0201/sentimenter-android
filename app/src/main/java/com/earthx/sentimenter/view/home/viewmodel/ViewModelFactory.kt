package com.earthx.sentimenter.view.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.earthx.sentimenter.data.repository.AnalyticsRepository
import com.earthx.sentimenter.data.repository.AuthRepository
import com.earthx.sentimenter.di.Injection
import com.earthx.sentimenter.view.home.HomeViewModel

class ViewModelFactory private constructor(private val mAuthRepository: AuthRepository, private val analyticRepository: AnalyticsRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideAuthRepository(context), Injection.provideAnalyticsRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(mAuthRepository, analyticRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}