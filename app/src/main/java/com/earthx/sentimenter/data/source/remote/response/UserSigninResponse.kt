package com.earthx.sentimenter.data.source.remote.response
import android.os.Parcelable
import com.earthx.sentimenter.data.model.User
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSigninResponse(
    @field:SerializedName("message")
    var message:String,
    @field:SerializedName("user")
    var user: User? = null,
    @field:SerializedName("status")
    var status: Int


): Parcelable
