package com.earthx.sentimenter.data.source.remote.response

import android.os.Parcelable
import com.earthx.sentimenter.data.model.ResultGraphItem
import com.earthx.sentimenter.data.model.User
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenerateGraphResponse(
    @field:SerializedName("message")
    var message:String,
    @field:SerializedName("data")
    var result: ArrayList<ResultGraphItem>? =null,
    @field:SerializedName("status")
    var status: Int


): Parcelable
