package com.earthx.sentimenter.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.earthx.sentimenter.data.model.User
import com.earthx.sentimenter.data.source.remote.datasource.AuthDataSource
import com.earthx.sentimenter.data.source.remote.RemoteDataSource
import com.earthx.sentimenter.data.source.remote.api.ApiResponse
import com.earthx.sentimenter.data.source.remote.api.StatusResponse
import com.earthx.sentimenter.data.source.remote.response.UserSignupResponse
import com.earthx.sentimenter.vo.Resource

class AuthRepository private constructor(private val remoteDataSource: RemoteDataSource,

) : AuthDataSource {



    companion object {
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(remoteData: RemoteDataSource): AuthRepository =
            instance
                ?: synchronized(this) {
                    instance
                        ?: AuthRepository(
                            remoteData
                        )
                            .apply { instance = this }
                }
    }



    override fun signin(email: String, password: String): LiveData<Resource<User>> {
        val result = MediatorLiveData<Resource<User>>()
        fun asLiveData(): LiveData<Resource<User>> = result
        val userData:LiveData<ApiResponse<User>> = remoteDataSource.signin(email, password)
        result.value = Resource.loading(null)

        result.addSource(userData){response->
            when(response.status){
                StatusResponse.SUCCESS -> result.value = Resource.success(response.body)

                StatusResponse.ERROR -> result.value = Resource.error(response.message, response.body)
            }
        }
        return asLiveData()

    }


    override fun signup(email: String, password: String): LiveData<Resource<UserSignupResponse>> {
        val result = MediatorLiveData<Resource<UserSignupResponse>>()
        fun asLiveData(): LiveData<Resource<UserSignupResponse>> = result
        val userData:LiveData<ApiResponse<UserSignupResponse>> = remoteDataSource.signup(email,password)
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