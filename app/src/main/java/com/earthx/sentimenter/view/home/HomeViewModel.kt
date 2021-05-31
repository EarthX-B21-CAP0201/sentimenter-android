package com.earthx.sentimenter.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.earthx.sentimenter.data.repository.AnalyticsRepository
import com.earthx.sentimenter.data.repository.AuthRepository
import com.earthx.sentimenter.data.source.remote.response.LastActivityResponse
import com.earthx.sentimenter.data.source.remote.response.UserSignoutResponse
import com.earthx.sentimenter.vo.Resource


class HomeViewModel(private val authRepository: AuthRepository, private val analyticRepository: AnalyticsRepository): ViewModel() {
    fun signout(token: String): LiveData<Resource<UserSignoutResponse>> = authRepository.signout(token);

    fun lastActivity(token: String): LiveData<Resource<LastActivityResponse>> = analyticRepository.getLastActivity(token)
}