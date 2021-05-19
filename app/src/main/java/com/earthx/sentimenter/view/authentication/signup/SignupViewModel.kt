package com.earthx.sentimenter.view.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.earthx.sentimenter.data.repository.AuthRepository
import com.earthx.sentimenter.data.source.remote.response.UserSignupResponse
import com.earthx.sentimenter.vo.Resource

class SignupViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun signup(email: String, password: String): LiveData<Resource<UserSignupResponse>> = authRepository.signup(email, password);
}