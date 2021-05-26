package com.earthx.sentimenter.view.analytics.graph

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.earthx.sentimenter.data.repository.AnalyticsRepository
import com.earthx.sentimenter.data.source.remote.response.GenerateGraphResponse
import com.earthx.sentimenter.vo.Resource
import java.util.*

class GraphViewModel(private val analyticsRepository: AnalyticsRepository): ViewModel() {
    fun generateTop10(token:String, keyword:String,
                      hashtag:String,
                      category:String,
                      language:String,
                      isRetweeted:Boolean,
                      isRealtime:Boolean,
                      dateStart: String,
                      dateEnd: String
    ): LiveData<Resource<GenerateGraphResponse>> = analyticsRepository.generateTop10( token,keyword,
        hashtag,category,language,isRetweeted,isRealtime,dateStart,dateEnd);
}