package com.haanbin.todayrecode.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navDirections.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(it)
            }
        })
    }
}