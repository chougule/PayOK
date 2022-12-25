package com.example.sampleapp.application.presentation.module.login.loginWithMobile

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.sampleapp.R
import com.example.sampleapp.application.presentation.custom.fragment.SubmitButtonFragment
import com.example.sampleapp.databinding.FragmentLoginWithMobileBinding

class LoginWithMobileFragment : SubmitButtonFragment() {

    private val viewModel: LoginWithMobileViewModel by fragmentViewModel()
    private var _binding: FragmentLoginWithMobileBinding? = null
    private val viewBinding get() = _binding!!

    override val layoutRes: Int = R.layout.fragment_login_with_mobile

    override val onSubmitClick: (View) -> Unit = {
        viewModel.submitMobile()
    }

    override val viewStubInflateListener: (ViewStub, View) -> Unit = { _, inflatedId ->
        _binding = FragmentLoginWithMobileBinding.bind(inflatedId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.editTextPhone.addTextChangedListener {
            viewModel.onMobileNumberUpdate(it.toString())
        }
        viewModel.onAsync(LoginWithMobileState::otpRequestSentAsync,
            uniqueOnly(), onSuccess = {
            withState(viewModel) { state ->
                state.mobileNumber?.let {
                    findNavController().navigate(
                        LoginWithMobileFragmentDirections.actionMobileLoginToEntryPin(it)
                    )
                }
            }
        }, onFail = {
            it.message?.let { errorMessage -> showErrorMessage(errorMessage) }
        })
    }

    private fun hideErrorMessage() {
        viewBinding.tilMobileNumber.error = null
    }

    private fun showErrorMessage(errorMessage: String) {
        viewBinding.tilMobileNumber.error = errorMessage
    }

    override fun invalidate() {
        withState(viewModel) {
            if (!it.isValidMobileNumber) {
                it.validationErrorMessage?.let { message -> showErrorMessage(message) }
            } else {
                hideErrorMessage()
            }
        }
    }
}