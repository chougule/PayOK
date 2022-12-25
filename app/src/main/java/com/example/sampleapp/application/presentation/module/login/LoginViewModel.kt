package com.example.sampleapp.application.presentation.module.login

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.example.domain.usecases.LoginRequest
import com.example.domain.usecases.LoginUseCase
import com.example.domain.usecases.util.response
import com.example.sampleapp.application.di.mavericks.AssistedViewModelFactory
import com.example.sampleapp.application.di.mavericks.hiltMavericksViewModelFactory
import com.example.sampleapp.application.presentation.util.Validator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class LoginViewModel @AssistedInject constructor(
    @Assisted initialState: LoginState,
    private val loginUseCase: LoginUseCase
) : MavericksViewModel<LoginState>(initialState = initialState) {

    fun login() {
        viewModelScope.launch {
            val state = awaitState()
            suspend {
                if (state.isValidPassword && state.isValidEmail) {
                    loginUseCase(LoginRequest(state.userId, state.password)).response
                } else {
                    throw IllegalStateException("Not valid parameters")
                }
            }.execute {
                copy(onAuthAsync = it)
            }
        }
    }

    fun onEmailTextUpdate(emailText: String) {
        val isValid = Validator.EmailIdValidator.validate(emailText)
        setState { copy(isValidEmail = isValid, userId = emailText, emailError = Validator.EmailIdValidator.errorMessage) }
    }

    fun onPasswordTextUpdate(passwordText: String) {
        val isValid = Validator.PasswordValidator.validate(passwordText)
        setState { copy(isValidPassword = isValid, passwordError = Validator.PasswordValidator.errorMessage) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<LoginViewModel, LoginState> {
        override fun create(initialState: LoginState): LoginViewModel
    }

    companion object : MavericksViewModelFactory<LoginViewModel, LoginState> by hiltMavericksViewModelFactory()
}
