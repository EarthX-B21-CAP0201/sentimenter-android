package com.earthx.sentimenter.view.authentication.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.earthx.sentimenter.data.model.User
import com.earthx.sentimenter.data.repository.AuthRepository
import com.earthx.sentimenter.vo.Resource


class SigninViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun signin(email: String, password: String): LiveData<Resource<User>> = authRepository.signin(email, password)

}