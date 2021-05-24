package com.earthx.sentimenter.data.source.remote.api

import com.earthx.sentimenter.BuildConfig
import com.earthx.sentimenter.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ApiService {
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"authentication/signin")
    fun signin(@Field("email") email: String, @Field("password") password: String): Call<UserSigninResponse>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"authentication/signup")
    fun signup(@Field("email") email: String, @Field("password") password: String): Call<UserSignupResponse>

    @POST(BuildConfig.BASE_URL+"authentication/signout")
    fun signout(@Header("x-access-token") token: String): Call<UserSignoutResponse>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"analytics/top-10/generate")
    fun generateTop10(@Header("x-access-token") token:String,
                      @Field("keyword") keyword:String,
                      @Field("hashtag") hashtag:String,
                      @Field("category") category:String,
                      @Field("language") language:String,
                      @Field("is_retweeted") isRetweeted:Boolean,
                      @Field("is_realtime") isRealtime:Boolean,
                      @Field("date_start") dateStart: String,
                      @Field("date_end") dateEnd:String
    ): Call<GenerateGraphResponse>

}