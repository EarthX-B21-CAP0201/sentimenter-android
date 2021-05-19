package com.earthx.sentimenter.data.source.remote.api

import com.earthx.sentimenter.BuildConfig
import com.earthx.sentimenter.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"authentication/signin")
    fun signin(@Field("email") email: String, @Field("password") password: String): Call<UserSigninResponse>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"authentication/signup")
    fun signup(@Field("email") email: String, @Field("password") password: String): Call<UserSignupResponse>

    @POST(BuildConfig.BASE_URL+"authentication/signout")
    fun signout(@Header("x-access-token") token: String): Call<UserSignoutResponse>


}