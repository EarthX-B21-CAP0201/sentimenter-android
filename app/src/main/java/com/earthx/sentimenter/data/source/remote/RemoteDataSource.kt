package com.earthx.sentimenter.data.source.remote

import android.content.Context
import com.earthx.sentimenter.data.source.local.sp.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.earthx.sentimenter.data.model.User
import com.earthx.sentimenter.data.source.remote.api.ApiCall
import com.earthx.sentimenter.data.source.remote.api.ApiCallback
import com.earthx.sentimenter.data.source.remote.api.ApiResponse
import com.earthx.sentimenter.data.source.remote.response.UserSigninResponse
import com.earthx.sentimenter.data.source.remote.response.UserSignoutResponse
import com.earthx.sentimenter.data.source.remote.response.UserSignupResponse

class RemoteDataSource private constructor(private val remoteDataSource: ApiCall) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private var ctx : Context? = null

        fun getInstance(helper: ApiCall, context: Context): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply {
                    instance = this
                    ctx = context
                }
            }
    }

    fun signin(email: String, password: String): LiveData<ApiResponse<User>> {
        val userLogged = MutableLiveData<ApiResponse<User>>()
        remoteDataSource.signin(
            email,password,
            object :
                ApiCallback<UserSigninResponse> {

            override fun onCallSuccess(user: UserSigninResponse) {
               val userData = User(
                   id = user.user!!.id,
                   email = user.user!!.email,
                   token = user.user!!.token,
               )
                userLogged.postValue(ApiResponse.success(userData))
                val sharedPreference =  ctx?.getSharedPreferences(SharedPreferences.loggedUser,Context.MODE_PRIVATE)
                var editor = sharedPreference?.edit()
                editor?.putString("id", user!!.user!!.id)
                editor?.putString("email",user!!.user!!.email)
                editor?.putString("token", user!!.user!!.token)
                editor?.commit()

            }
            override fun onCallFailed(value: UserSigninResponse) {
                val message = value.message
                val emptyUser = User(
                    id = "",
                    email = "",
                    token = ""
                )
                userLogged.postValue(ApiResponse.error(message, emptyUser))

            }
            override fun onCallError(throwable: Throwable) {
                val emptyUser = User(
                    id = "",
                    email = "",
                    token = ""
                )
                userLogged.postValue(ApiResponse.error(throwable.message?:"", emptyUser))
                Log.d("ERROR", throwable.message ?: "")
            }

            })
        return userLogged
    }

    fun signup(email: String, password: String): LiveData<ApiResponse<UserSignupResponse>> {
        val data = MutableLiveData<ApiResponse<UserSignupResponse>>()
        remoteDataSource.signup(
            email, password,
            object :
                ApiCallback<UserSignupResponse> {
                override fun onCallSuccess(user: UserSignupResponse) {
                    data.postValue(ApiResponse.success(user))
                }
                override fun onCallError(throwable: Throwable) {
                    val emptyUser = UserSignupResponse(
                        message = "",
                        status = 403
                    )
                    data.postValue(ApiResponse.error(throwable.message?:"", emptyUser))
                    Log.d("ERROR", throwable.message ?: "")
                }

                override fun onCallFailed(value: UserSignupResponse) {
                    data.postValue(ApiResponse.error(value.message, value))

                }

            })
        return data
    }

    fun signout(token: String): LiveData<ApiResponse<UserSignoutResponse>> {
        val data = MutableLiveData<ApiResponse<UserSignoutResponse>>()
        remoteDataSource.signout(
           token,
            object :
                ApiCallback<UserSignoutResponse> {
                override fun onCallSuccess(res: UserSignoutResponse) {
                    val sharedPreference =  ctx?.getSharedPreferences(SharedPreferences.loggedUser,Context.MODE_PRIVATE)
                    var editor = sharedPreference?.edit()
                    editor?.remove("id")
                    editor?.remove("email")
                    editor?.remove("token")
                    editor?.commit()
                    data.postValue(ApiResponse.success(res))
                }
                override fun onCallError(throwable: Throwable) {
                    val emptyUser = UserSignoutResponse(
                        message = "",
                        status = 403
                    )
                    data.postValue(ApiResponse.error(throwable.message?:"", emptyUser))
                    Log.d("ERROR", throwable.message ?: "")
                }

                override fun onCallFailed(value: UserSignoutResponse) {
                    data.postValue(ApiResponse.error(value.message, value))

                }

            })
        return data
    }

}