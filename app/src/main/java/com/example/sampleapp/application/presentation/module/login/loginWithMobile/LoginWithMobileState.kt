package com.example.sampleapp.application.presentation.module.login.loginWithMobile

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class LoginWithMobileState(
    val mobileNumber: String? = null,
    val validationErrorMessage: String? = null,
    val isValidMobileNumber: Boolean = false,
    val otpRequestSentAsync: Async<Boolean?> = Uninitialized) : MavericksState
