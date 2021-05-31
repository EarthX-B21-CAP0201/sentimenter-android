package com.earthx.sentimenter.view.analytics.sentiment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.earthx.sentimenter.data.repository.AnalyticsRepository
import com.earthx.sentimenter.data.source.remote.response.GenerateSentimentResponse
import com.earthx.sentimenter.vo.Resource

class SentimentViewModel(private val analyticsRepository: AnalyticsRepository): ViewModel() {
    fun generateSentiment(token:String, keyword:String
    ): LiveData<Resource<GenerateSentimentResponse>> = analyticsRepository.generateSentiment(token,keyword);

}