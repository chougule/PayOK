package com.example.sampleapp.application.presentation.module.login.setpin

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.example.domain.usecases.PinAuthRequest
import com.example.domain.usecases.PinAuthUseCase
import com.example.domain.usecases.util.response
import com.example.sampleapp.application.di.mavericks.AssistedViewModelFactory
import com.example.sampleapp.application.di.mavericks.hiltMavericksViewModelFactory
import com.example.sampleapp.application.presentation.util.Validator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PinEntryViewModel @AssistedInject constructor(
    @Assisted initialState: PinEntryState,
    val pinAuthUseCase: PinAuthUseCase,
) : MavericksViewModel<PinEntryState>(initialState) {

    fun submitPin() {
        viewModelScope.launch {
            val pin = awaitState().pinText
            val username = awaitState().username
            suspend {
                if (!username.isNullOrBlank() && !pin.isNullOrBlank()) {
                    pinAuthUseCase(PinAuthRequest(username, pin)).response
                } else {
                    throw IllegalStateException("No valid parameters")
                }
            }.execute {
                copy(pinAuthenticationAsync = it)
            }
        }
    }

    fun updatePin(pinText: String) {
        setState {
            copy(pinText = pinText, enableSubmit = Validator.OtpValidator.validate(pinText))
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PinEntryViewModel, PinEntryState> {
        override fun create(initialState: PinEntryState): PinEntryViewModel
    }

    companion object : MavericksViewModelFactory<PinEntryViewModel, PinEntryState> by hiltMavericksViewModelFactory()

}