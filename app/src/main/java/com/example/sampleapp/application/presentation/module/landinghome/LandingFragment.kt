package com.example.sampleapp.application.presentation.module.landinghome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sampleapp.R
import com.example.sampleapp.application.presentation.base.BaseMavericksFragment
import com.example.sampleapp.databinding.FragmentLandingBinding

class LandingFragment : BaseMavericksFragment() {

    private var _binding: FragmentLandingBinding? = null
    private val viewBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.navigation.setupWithNavController(findNavController())
        viewBinding.showBottomNav = true
    }
}