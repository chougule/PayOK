package com.example.sampleapp.application.presentation.module.login.setpin

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.example.domain.usecases.OTPAuthResponse
import com.example.domain.usecases.util.UseCaseResult

data class PinEntryState(val pinText: String? = null,
                         val username: String? = null,
                         val pinAuthenticationAsync: Async<UseCaseResult<OTPAuthResponse>?> = Uninitialized,
                         val enableSubmit: Boolean = false) : MavericksState {
    constructor(arg: String) : this(username = arg)
}