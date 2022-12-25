package com.example.sampleapp.application.presentation.module.login.confirmpin

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.example.domain.usecases.OTPAuthResponse
import com.example.domain.usecases.util.UseCaseResult

class ConfirmPinState (val pinText: String? = null,
                       val username: String? = null,
                       val pinAuthenticationAsync: Async<UseCaseResult<OTPAuthResponse>?> = Uninitialized,
                       val enableSubmit: Boolean = false) : MavericksState {
    constructor(arg: String) : this(username = arg)
}