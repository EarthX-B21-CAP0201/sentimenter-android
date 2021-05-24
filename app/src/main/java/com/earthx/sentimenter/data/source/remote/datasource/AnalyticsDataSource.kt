package com.earthx.sentimenter.data.source.remote.datasource

import androidx.lifecycle.LiveData
import com.earthx.sentimenter.data.source.remote.response.GenerateGraphResponse
import com.earthx.sentimenter.vo.Resource
import java.util.*

interface AnalyticsDataSource {
    fun generateTop10(token: String,
                      keyword:String,
                      hashtag:String,
                      category:String,
                      language:String,
                      isRetweeted:Boolean,
                      isRealtime:Boolean,
                      dateStart: String,
                      dateEnd: String): LiveData<Resource<GenerateGraphResponse>>
}