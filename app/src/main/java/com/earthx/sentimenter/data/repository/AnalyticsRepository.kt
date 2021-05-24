package com.earthx.sentimenter.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.earthx.sentimenter.data.model.User
import com.earthx.sentimenter.data.source.remote.datasource.AuthDataSource
import com.earthx.sentimenter.data.source.remote.RemoteDataSource
import com.earthx.sentimenter.data.source.remote.api.ApiResponse
import com.earthx.sentimenter.data.source.remote.api.StatusResponse
import com.earthx.sentimenter.data.source.remote.datasource.AnalyticsDataSource
import com.earthx.sentimenter.data.source.remote.response.GenerateGraphResponse
import com.earthx.sentimenter.data.source.remote.response.UserSignoutResponse
import com.earthx.sentimenter.data.source.remote.response.UserSignupResponse
import com.earthx.sentimenter.vo.Resource
import java.util.*

class AnalyticsRepository private constructor(private val remoteDataSource: RemoteDataSource,

                                         ) : AnalyticsDataSource {



    companion object {
        @Volatile
        private var instance: AnalyticsRepository? = null

        fun getInstance(remoteData: RemoteDataSource): AnalyticsRepository =
            instance
                ?: synchronized(this) {
                    instance
                        ?: AnalyticsRepository(
                            remoteData
                        )
                            .apply { instance = this }
                }
    }


    override fun generateTop10(
        token: String,
        keyword: String,
        hashtag: String,
        category: String,
        language: String,
        isRetweeted: Boolean,
        isRealtime: Boolean,
        dateStart: String,
        dateEnd: String
    ): LiveData<Resource<GenerateGraphResponse>> {
        val result = MediatorLiveData<Resource<GenerateGraphResponse>>()
        fun asLiveData(): LiveData<Resource<GenerateGraphResponse>> = result
        val userData:LiveData<ApiResponse<GenerateGraphResponse>> = remoteDataSource.generateTop10(token,keyword,
            hashtag,category,language,isRetweeted,isRealtime,dateStart,dateEnd)
        result.value = Resource.loading(null)

        result.addSource(userData){response->
            when(response.status){
                StatusResponse.SUCCESS -> result.value = Resource.success(response.body)
                StatusResponse.ERROR -> result.value = Resource.error(response.message, response.body)
            }
        }
        return asLiveData()

    }
}