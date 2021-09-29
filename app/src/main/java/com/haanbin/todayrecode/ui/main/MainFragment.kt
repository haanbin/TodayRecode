package com.haanbin.todayrecode.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.run {
            setupWithNavController(
                Navigation.findNavController(
                    requireView().findViewById(
                        R.id.main_nav_host_fragment
                    )
                )
            )
            setOnItemReselectedListener { }
        }
    }
}