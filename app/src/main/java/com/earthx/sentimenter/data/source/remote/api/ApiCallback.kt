package com.earthx.sentimenter.data.source.remote.api

interface ApiCallback<T> {
    fun onCallSuccess(value:T)
    fun onCallFailed(value: T)
    fun onCallError(throwable: Throwable)

}