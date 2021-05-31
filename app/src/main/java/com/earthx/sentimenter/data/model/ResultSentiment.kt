package com.earthx.sentimenter.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultSentiment(
    @field:SerializedName("percentage")
    val percentage: Int,
    @field:SerializedName("sentiment")
    val sentiment: String,
    @field:SerializedName("name")
    val name: String

): Parcelable