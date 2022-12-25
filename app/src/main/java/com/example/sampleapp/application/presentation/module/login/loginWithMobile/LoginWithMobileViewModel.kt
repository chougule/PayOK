package com.example.sampleapp.application.presentation.module.login.loginWithMobile

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.example.domain.usecases.RequestOTPUseCase
import com.example.domain.usecases.util.response
import com.example.sampleapp.application.di.mavericks.AssistedViewModelFactory
import com.example.sampleapp.application.di.mavericks.hiltMavericksViewModelFactory
import com.example.sampleapp.application.presentation.util.Validator
import com.example.sampleapp.application.presentation.util.Validator.MobileValidator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class LoginWithMobileViewModel @AssistedInject constructor(
    @Assisted initialState: LoginWithMobileState,
    val requestOTPUseCase: RequestOTPUseCase,
) : MavericksViewModel<LoginWithMobileState>(initialState) {

    fun onMobileNumberUpdate(newNumber: String) {
        with(MobileValidator) {
            val isValid = validate(newNumber)
            setState {
                copy(
                    mobileNumber = newNumber,
                    validationErrorMessage = errorMessage.orEmpty(),
                    isValidMobileNumber = isValid
                )
            }
        }
    }

    fun submitMobile() {
            viewModelScope.launch {
                val mobileNumber = awaitState().mobileNumber.orEmpty()
                suspend {
                    requestOTPUseCase(mobileNumber).response
                }.execute {
                    copy(otpRequestSentAsync = it)
                }
            }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<LoginWithMobileViewModel, LoginWithMobileState> {
        override fun create(initialState: LoginWithMobileState): LoginWithMobileViewModel
    }

    companion object : MavericksViewModelFactory<LoginWithMobileViewModel, LoginWithMobileState> by hiltMavericksViewModelFactory()

}
