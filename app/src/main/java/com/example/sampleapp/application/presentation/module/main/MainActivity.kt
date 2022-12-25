package com.example.sampleapp.application.presentation.module.main

import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.viewModel
import com.example.sampleapp.R
import com.example.sampleapp.application.presentation.base.BaseActivity
import com.example.sampleapp.application.presentation.module.authentication.AuthState
import com.example.sampleapp.application.presentation.module.authentication.AuthenticationViewModel
import com.example.sampleapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), MavericksView, ProgressViewHandler {

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    private val authViewModel: AuthenticationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)
        splashScreen.setKeepOnScreenCondition { false }
        authViewModel.onEach(AuthState::isAuthenticated, uniqueOnly()) { isUserAuthenticated ->
            if (isUserAuthenticated) {
                navigateToLanding()
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLanding() {
        navController.navigate(MainFragmentDirections.actionNavigateToLanding())
    }

    private fun navigateToLogin() {
        navController.navigate(MainFragmentDirections.actionAuthenticateUser())
        //navController.navigate(MainFragmentDirections.actionNavigateToMobileLogin())
    }

    override fun invalidate() {
    }

    override fun showProgressView(show: Boolean) {
        binding.blockingProgressView.root.visibility = if (show) View.VISIBLE else View.GONE
    }
}

interface ProgressViewHandler {
    fun showProgressView(show: Boolean)
}