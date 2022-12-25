package com.example.sampleapp.application.presentation.base

import androidx.fragment.app.Fragment
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.example.sampleapp.application.presentation.module.main.ProgressViewHandler

/**
 *  A base of all fragment
 */
open class BaseFragment : Fragment() {
    fun showBlockingProgressView(asyncField: Async<*>) {
        requireActivity().let { activity ->
            if (activity is ProgressViewHandler) {
                activity.showProgressView(asyncField is Loading)
            }
        }
    }
}