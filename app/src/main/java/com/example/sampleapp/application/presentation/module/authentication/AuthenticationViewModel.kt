package com.example.sampleapp.application.presentation.module.authentication

import android.content.SharedPreferences
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.example.sampleapp.application.di.mavericks.AssistedViewModelFactory
import com.example.sampleapp.application.di.mavericks.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthenticationViewModel @AssistedInject constructor(
    @Assisted initialState: AuthState,
    private val pref: SharedPreferences,
) : MavericksViewModel<AuthState>(initialState) {

    init {
        authenticate()
    }

    private fun authenticate() {
        setState {
            copy(isAuthenticated = pref.getBoolean("isAuth", false))
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<AuthenticationViewModel, AuthState> {
        override fun create(initialState: AuthState): AuthenticationViewModel
    }

    companion object : MavericksViewModelFactory<AuthenticationViewModel, AuthState> by hiltMavericksViewModelFactory()

}