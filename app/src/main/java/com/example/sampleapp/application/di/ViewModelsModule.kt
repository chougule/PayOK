package com.example.sampleapp.application.di

import com.example.sampleapp.application.di.mavericks.AssistedViewModelFactory
import com.example.sampleapp.application.di.mavericks.MavericksViewModelComponent
import com.example.sampleapp.application.di.mavericks.ViewModelKey
import com.example.sampleapp.application.presentation.module.authentication.AuthenticationViewModel
import com.example.sampleapp.application.presentation.module.login.LoginViewModel
import com.example.sampleapp.application.presentation.module.login.loginWithMobile.LoginWithMobileViewModel
import com.example.sampleapp.application.presentation.module.login.setpin.PinEntryViewModel
import com.example.sampleapp.application.presentation.module.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun mainActivityViewModelFactory(factory: MainActivityViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(AuthenticationViewModel::class)
    fun authenticationViewModelFactory(factory: AuthenticationViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(LoginWithMobileViewModel::class)
    fun loginWithMobileViewModelFactory(factory: LoginWithMobileViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModelFactory(factory: LoginViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(PinEntryViewModel::class)
    fun pinEntryViewModelFactory(factory: PinEntryViewModel.Factory): AssistedViewModelFactory<*, *>

}