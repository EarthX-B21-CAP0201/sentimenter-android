package com.earthx.sentimenter.data.source.remote.response

import android.os.Parcelable
import com.earthx.sentimenter.data.model.ResultSentiment
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenerateSentimentResponse(
    @field:SerializedName("message")
    var message:String,
    @field:SerializedName("data")
    var result: ResultSentiment? =null,
    @field:SerializedName("status")
    var status: Int

): Parcelable