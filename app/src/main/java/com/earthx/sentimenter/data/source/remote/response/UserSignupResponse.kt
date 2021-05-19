package com.earthx.sentimenter.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSignupResponse(
    @field:SerializedName("message")
    var message:String,
    @field:SerializedName("status")
    var status: Int
): Parcelable
