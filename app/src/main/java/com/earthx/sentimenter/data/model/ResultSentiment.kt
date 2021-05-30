package com.earthx.sentimenter.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultSentiment(
    @field:SerializedName("percentage")
    val index: Int,
    @field:SerializedName("sentiment")
    val count: Int

): Parcelable