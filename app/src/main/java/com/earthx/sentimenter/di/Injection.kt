package com.earthx.sentimenter.di

import android.content.Context
import com.earthx.sentimenter.data.repository.AnalyticsRepository
import com.earthx.sentimenter.data.repository.AuthRepository
import com.earthx.sentimenter.data.source.remote.RemoteDataSource
import com.earthx.sentimenter.data.source.remote.api.ApiCall


object Injection {
    fun provideAuthRepository(context: Context): AuthRepository {


        val remoteDataSource = RemoteDataSource.getInstance(ApiCall(), context)


        return AuthRepository.getInstance(remoteDataSource)
    }
    fun provideAnalyticsRepository(context: Context):AnalyticsRepository{
        val remoteDataSource = RemoteDataSource.getInstance(ApiCall(), context)

        return AnalyticsRepository.getInstance(remoteDataSource)

    }
}