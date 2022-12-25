package com.example.sampleapp.application.presentation.custom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import com.example.sampleapp.R
import com.example.sampleapp.application.presentation.base.BaseMavericksFragment
import com.example.sampleapp.databinding.FragmentSubmitButtonBinding

abstract class SubmitButtonFragment : BaseMavericksFragment() {

    private var submitButtonText: String = ""
    protected var isEnableSubmitButton: Boolean = false
    private var _binding: FragmentSubmitButtonBinding? = null
    private val viewBinding get() = _binding!!

    abstract val layoutRes: Int
    abstract val onSubmitClick: (View) -> Unit
    abstract val viewStubInflateListener: (ViewStub, View) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSubmitButtonBinding.inflate(inflater, container, false)
        viewBinding.btnSubmit.text = submitButtonText.ifBlank { getString(R.string.submit) }
        viewBinding.viewStub.setOnInflateListener(viewStubInflateListener)
        viewBinding.viewStub.apply {
            layoutResource = layoutRes
        }.also {
            it.inflate()
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnSubmit.setOnClickListener(onSubmitClick)
    }

    override fun invalidate() {
        super.invalidate()
        viewBinding.btnSubmit.isEnabled = isEnableSubmitButton
    }
}