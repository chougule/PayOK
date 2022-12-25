package com.example.sampleapp.application.presentation.base.epoxy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.MvRxView
import com.example.sampleapp.R
import com.example.sampleapp.application.presentation.base.BaseFragment

abstract class EpoxyBaseFragment : BaseFragment(), MvRxView {

    protected lateinit var recyclerView: EpoxyRecyclerView
    protected val epoxyController by lazy { epoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epoxy_base, container, false).apply {
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.setController(epoxyController)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    abstract fun epoxyController(): SampleEpoxyController

    override fun invalidate() {
        recyclerView.requestModelBuild()
    }
}