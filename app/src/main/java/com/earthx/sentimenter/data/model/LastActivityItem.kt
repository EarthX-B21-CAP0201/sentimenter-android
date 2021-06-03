package com.earthx.sentimenter.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastActivityItem(
    @field:SerializedName("user")
    val user: String,
    @field:SerializedName("date_created")
    val date: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("keyword")
    val keyword: String,
    @field:SerializedName("result")
    val result : ResultSentiment
): Parcelable