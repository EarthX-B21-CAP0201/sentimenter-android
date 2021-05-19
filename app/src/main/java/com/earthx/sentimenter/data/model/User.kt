package com.earthx.sentimenter.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @field:SerializedName("id")
    var id: String,

    @field:SerializedName("email")
    var email: String,

    @field:SerializedName("token")
    var token: String? = null,

    ): Parcelable