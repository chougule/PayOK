package com.example.sampleapp.application.presentation.module.login.setpin

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.View
import android.view.ViewStub
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.domain.usecases.util.UseCaseResult
import com.example.sampleapp.R
import com.example.sampleapp.application.presentation.custom.fragment.SubmitButtonFragment
import com.example.sampleapp.application.presentation.extensions.showToast
import com.example.sampleapp.application.presentation.util.Validator
import com.example.sampleapp.databinding.FragmentPinEntryBinding

class PinEntryFragment : SubmitButtonFragment() {

    private val viewModel: PinEntryViewModel by fragmentViewModel()
    private var _binding: FragmentPinEntryBinding? = null
    private val viewBinding get() = _binding!!

    override val layoutRes = R.layout.fragment_pin_entry

    override val onSubmitClick: (View) -> Unit = {
        viewModel.submitPin()
    }

    override val viewStubInflateListener: (ViewStub, View) -> Unit = { _, inflatedId ->
        _binding = FragmentPinEntryBinding.bind(inflatedId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.editTextPin.filters = arrayOf<InputFilter>(LengthFilter(Validator.OTP_LENGTH))
        viewBinding.editTextPin.addTextChangedListener {
            viewModel.updatePin(it.toString())
        }

        viewBinding.textForgotPin.setOnClickListener {
            showToast("Coming Soon...")
        }

        viewModel.onAsync(PinEntryState::pinAuthenticationAsync, uniqueOnly(),
            onSuccess = {
                when (it) {
                    is UseCaseResult.Success -> {
                        showToast(it.response.businessName)
                    }
                    is UseCaseResult.Error -> {
                        showToast(it.exception.message)
                    }
                    is UseCaseResult.Loading -> {
                        showToast("Loading...")
                    }
                    else -> {
                        showToast("Unknown")
                    }
                }
            },
            onFail = {
                showToast(it.message)
            }
        )
        viewModel.onEach(PinEntryState::pinAuthenticationAsync, uniqueOnly()) {
            showBlockingProgressView(it)
            if (it is Success) {
                findNavController().navigate(PinEntryFragmentDirections.actionPinFragmentToLandingFragment())
            }
        }
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            isEnableSubmitButton = state.enableSubmit
        }
        super.invalidate()
    }
}