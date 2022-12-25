package com.example.sampleapp.application.presentation.module.main

import com.airbnb.mvrx.MavericksState

data class MainActivityState(val isUserLoggedIn: Boolean = false,
                             val isDataReady: Boolean = true) : MavericksState {

}
