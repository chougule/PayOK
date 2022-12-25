package com.example.sampleapp.application.presentation.module.login

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.example.domain.usecases.util.LoginResponse
import com.example.domain.usecases.util.UseCaseResult

data class LoginState(
    val isValidEmail: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isValidPassword: Boolean = false,
    val userId: String = "",
    val password: String = "",
    val onAuthAsync: Async<UseCaseResult<LoginResponse>?> = Uninitialized
) : MavericksState {
    val enableSubmit = isValidEmail && isValidPassword
}