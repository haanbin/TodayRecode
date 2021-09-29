package com.haanbin.todayrecode.ui.main.today

import androidx.fragment.app.viewModels
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentTodayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : BaseFragment<FragmentTodayBinding, TodayViewModel>(R.layout.fragment_today) {

    override val viewModel: TodayViewModel by viewModels()
}