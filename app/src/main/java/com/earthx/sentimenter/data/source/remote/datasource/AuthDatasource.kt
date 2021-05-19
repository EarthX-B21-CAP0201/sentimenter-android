package com.earthx.sentimenter.data.source.remote.datasource

import androidx.lifecycle.LiveData
import com.earthx.sentimenter.data.model.User
import com.earthx.sentimenter.data.source.remote.response.UserSignoutResponse
import com.earthx.sentimenter.data.source.remote.response.UserSignupResponse
import com.earthx.sentimenter.vo.Resource

interface AuthDataSource {
    fun signin(email: String, password: String): LiveData<Resource<User>>
    fun signup(email: String, password: String): LiveData<Resource<UserSignupResponse>>
    fun signout(token: String): LiveData<Resource<UserSignoutResponse>>
}