package com.earthx.sentimenter.view.analytics.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.earthx.sentimenter.data.repository.AnalyticsRepository

import com.earthx.sentimenter.di.Injection
import com.earthx.sentimenter.view.analytics.graph.GraphViewModel
import com.earthx.sentimenter.view.analytics.sentiment.SentimentViewModel

class ViewModelFactory private constructor(private val mAnalyticsRepository: AnalyticsRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideAnalyticsRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(GraphViewModel::class.java) -> {
                return GraphViewModel(mAnalyticsRepository) as T
            }
            modelClass.isAssignableFrom(SentimentViewModel::class.java) -> {
                return SentimentViewModel(mAnalyticsRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}