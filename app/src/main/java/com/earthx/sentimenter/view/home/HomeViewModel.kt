package com.earthx.sentimenter.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.earthx.sentimenter.data.repository.AuthRepository
import com.earthx.sentimenter.data.source.remote.response.UserSignoutResponse
import com.earthx.sentimenter.vo.Resource


class HomeViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun signout(token: String): LiveData<Resource<UserSignoutResponse>> = authRepository.signout(token);
}