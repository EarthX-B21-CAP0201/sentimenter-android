package com.earthx.sentimenter.data.source.remote.api

import android.content.ContentValues
import android.util.Log
import com.earthx.sentimenter.data.source.remote.response.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import java.util.*
import kotlin.collections.ArrayList

class ApiCall() {
    fun signin(email: String, password: String, callback: ApiCallback<UserSigninResponse>){
        try{
            val client = ApiConfig.getApiService().signin(email, password)
            client.enqueue(object: Callback<UserSigninResponse>{
                override fun onResponse(
                    call: Call<UserSigninResponse>,
                    response: Response<UserSigninResponse>
                ) {
                    if (response.isSuccessful) {
                        val userData = response.body()!!.user
                        val message = response.body()!!.message
                        val userResponse = UserSigninResponse(
                            message = message,
                            user = userData,
                            status = response.code()
                        )
                        callback.onCallSuccess(userResponse)
                    } else {
                        Log.d("error-msg", "onFailure: ${response.message()}")
                        val userResponse = UserSigninResponse(
                            message = "wrong email or password",
                            status = response.code()
                        )
                        callback.onCallFailed(userResponse)

                    }
                }
                override fun onFailure(call: Call<UserSigninResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e("error-msg", "onFailure: ${t.message.toString()}")
                }
            })
        }
        catch(e: JSONException){
            e.printStackTrace()
        }
    }

    fun signup(email: String,password : String, callback: ApiCallback<UserSignupResponse>){
        try{
            val client = ApiConfig.getApiService().signup(email, password)
            client.enqueue(object: Callback<UserSignupResponse>{
                override fun onResponse(
                    call: Call<UserSignupResponse>,
                    response: Response<UserSignupResponse>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()!!.message
                        val userResponse = UserSignupResponse(
                            message = message,
                            status = response.code()
                        )
                        callback.onCallSuccess(userResponse)
                    } else {
                        val userResponse = UserSignupResponse(
                            message = "email already exist",
                            status = response.code()
                        )
                        callback.onCallFailed(userResponse)
                        Log.d("error-msg", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<UserSignupResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        catch(e: JSONException){
            e.printStackTrace()
        }
    }

    fun signout(token: String, callback: ApiCallback<UserSignoutResponse>){
        try{
            val client = ApiConfig.getApiService().signout(token)
            client.enqueue(object: Callback<UserSignoutResponse>{
                override fun onResponse(
                    call: Call<UserSignoutResponse>,
                    response: Response<UserSignoutResponse>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()!!.message
                        val userResponse = UserSignoutResponse(
                            message = message,
                            status = response.code()
                        )
                        callback.onCallSuccess(userResponse)
                    } else {
                        val userResponse = UserSignoutResponse(
                            message = "something went wrong",
                            status = response.code()
                        )
                        callback.onCallFailed(userResponse)
                        Log.d("error-msg", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<UserSignoutResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        catch(e: JSONException){
            e.printStackTrace()
        }
    }

    fun generateGraph(token: String,
                        keyword:String,
                        hashtag:String,
                        category:String,
                        language:String,
                        isRetweeted:Boolean,
                        isRealtime:Boolean,
                        dateStart: String,
                        dateEnd: String,
                        callback: ApiCallback<GenerateGraphResponse>){
        try{
            val client = ApiConfig.getApiService().generateTop10(token, keyword, hashtag, category, language, isRetweeted, isRealtime, dateStart, dateEnd)
            client.enqueue(object: Callback<GenerateGraphResponse>{
                override fun onResponse(
                    call: Call<GenerateGraphResponse>,
                    response: Response<GenerateGraphResponse>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()!!.message
                        val results = response.body()!!.result
                        val graphResponse = GenerateGraphResponse(
                            message = message,
                            status = response.code(),
                            result = results
                        )
                        callback.onCallSuccess(graphResponse)
                    } else {
                        val graphResponse = GenerateGraphResponse(
                            message = "something went wrong",
                            status = response.code()
                        )
                        callback.onCallFailed(graphResponse)
                        Log.d("error-msg", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<GenerateGraphResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        catch(e: JSONException){
            e.printStackTrace()
        }
    }

    fun generateSentiment(token: String,
                      keyword:String, language:String,
                      callback: ApiCallback<GenerateSentimentResponse>){
        try{
            val client = ApiConfig.getApiService().generateSentiment(token, keyword, language)
            client.enqueue(object: Callback<GenerateSentimentResponse>{
                override fun onResponse(
                    call: Call<GenerateSentimentResponse>,
                    response: Response<GenerateSentimentResponse>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()!!.message
                        val results = response.body()!!.result
                        val graphResponse = GenerateSentimentResponse(
                            message = message,
                            status = response.code(),
                            result = results
                        )
                        callback.onCallSuccess(graphResponse)
                    } else {
                        val graphResponse = GenerateSentimentResponse(
                            message = "something went wrong",
                            status = response.code()
                        )
                        callback.onCallFailed(graphResponse)
                        Log.d("error-msg", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<GenerateSentimentResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        catch(e: JSONException){
            e.printStackTrace()
        }
    }

    fun getLastActivity(token: String,
                          callback: ApiCallback<LastActivityResponse>){
        try{
            val client = ApiConfig.getApiService().getLastActivity(token)
            client.enqueue(object: Callback<LastActivityResponse>{
                override fun onResponse(
                    call: Call<LastActivityResponse>,
                    response: Response<LastActivityResponse>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()!!.message
                        val results = response.body()!!.result
                        val lastActivityResponse = LastActivityResponse(
                            message = message,
                            status = response.code(),
                            result = results
                        )
                        callback.onCallSuccess(lastActivityResponse)
                    } else {
                        val lastActivityResponse = LastActivityResponse(
                            message = "something went wrong",
                            status = response.code(),
                            result = ArrayList()
                        )
                        callback.onCallFailed(lastActivityResponse)
                        Log.d("error-msg", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<LastActivityResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        catch(e: JSONException){
            e.printStackTrace()
        }
    }

}