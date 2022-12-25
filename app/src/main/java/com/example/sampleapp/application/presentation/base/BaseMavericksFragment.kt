package com.example.sampleapp.application.presentation.base

import com.airbnb.mvrx.MavericksView

open class BaseMavericksFragment : BaseFragment(), MavericksView {
    override fun invalidate() {
    }
}