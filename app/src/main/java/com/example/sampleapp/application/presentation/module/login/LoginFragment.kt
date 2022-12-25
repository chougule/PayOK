package com.example.sampleapp.application.presentation.module.login

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.domain.usecases.util.response
import com.example.sampleapp.R
import com.example.sampleapp.application.presentation.custom.fragment.SubmitButtonFragment
import com.example.sampleapp.application.presentation.extensions.showToast
import com.example.sampleapp.databinding.FragmentLoginBinding

class LoginFragment : SubmitButtonFragment() {

    private val viewModel: LoginViewModel by fragmentViewModel()
    private var _binding: FragmentLoginBinding? = null
    private val viewBinding get() = _binding!!

    override val layoutRes: Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.etEmailAddress.addTextChangedListener {
            viewModel.onEmailTextUpdate(it.toString())
        }
        viewBinding.etPassword.addTextChangedListener {
            viewModel.onPasswordTextUpdate(it.toString())
        }
        viewModel.onEach(LoginState::onAuthAsync, uniqueOnly()) {
            showBlockingProgressView(it)
            if (it is Success) {
                it()?.response?.isSuccess?.let { success ->
                    withState(viewModel) { state ->
                        if (success) {
                            findNavController().navigate(
                                LoginFragmentDirections.actionLoginFragmentToPinEntryFragment(state.userId))
                        }
                    }
                }
            } else if (it is Fail) {
                showToast(R.string.genericErrorMessage)
            }
        }
    }

    override val viewStubInflateListener: (ViewStub, View) -> Unit = { _, inflatedId ->
        _binding = FragmentLoginBinding.bind(inflatedId)
    }

    override val onSubmitClick: (View) -> Unit = {
        viewModel.login()
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            isEnableSubmitButton = state.enableSubmit
            viewBinding.tiLayoutPassword.error = state.passwordError
            viewBinding.tiLayoutUserId.error = state.emailError
        }
        super.invalidate()
    }
}