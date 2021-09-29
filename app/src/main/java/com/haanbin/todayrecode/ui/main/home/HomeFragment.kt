package com.haanbin.todayrecode.ui.main.home

import androidx.fragment.app.viewModels
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()
}