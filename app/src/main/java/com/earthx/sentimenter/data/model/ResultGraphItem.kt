package com.earthx.sentimenter.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultGraphItem(
    @field:SerializedName("index")
    val index: Int,
    @field:SerializedName("count")
    val count: Int,
    @field:SerializedName("name")
    val name: String
): Parcelable