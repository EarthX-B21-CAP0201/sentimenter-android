package com.earthx.sentimenter.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultSentiment(
    @field:SerializedName("percentage")
    val percentage: Int? =0,
    @field:SerializedName("sentiment")
    val sentiment: String? = "",
    @field:SerializedName("name")
    val name: String? ="",
    @field:SerializedName("total_tweet")
    val total_tweet: Int? =0

): Parcelable