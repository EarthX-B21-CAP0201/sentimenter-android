package com.earthx.sentimenter.data.source.remote.api

import android.content.ContentValues
import android.util.Log
import com.earthx.sentimenter.data.source.remote.response.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


}