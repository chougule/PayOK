package com.example.sampleapp.application.presentation.module.main

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.example.sampleapp.application.di.mavericks.AssistedViewModelFactory
import com.example.sampleapp.application.di.mavericks.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * a MainActivityViewModel will be used to set initial business related setups,
 * but for some viewmodel error reason it won't work, will solve it later
 */
class MainActivityViewModel
@AssistedInject constructor(@Assisted initialState: MainActivityState) : MavericksViewModel<MainActivityState>(initialState) {

    fun onSplashScreenSet() {
        setState {
            // Do async operations here to show splash screen for long time, currently used bool for simplicity sec
            copy(isDataReady = true)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<MainActivityViewModel, MainActivityState> {
        override fun create(state: MainActivityState): MainActivityViewModel
    }

    companion object : MavericksViewModelFactory<MainActivityViewModel, MainActivityState> by hiltMavericksViewModelFactory()
}
