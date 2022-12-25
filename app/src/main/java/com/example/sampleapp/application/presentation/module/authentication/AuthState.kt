package com.example.sampleapp.application.presentation.module.authentication

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class AuthState(
    val authenticateAsync: Async<Any> = Uninitialized,
    val isAuthenticated: Boolean = false) : MavericksState {
}