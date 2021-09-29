package com.haanbin.todayrecode.ui.main.schedule

import androidx.fragment.app.viewModels
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentScheduleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment :
    BaseFragment<FragmentScheduleBinding, ScheduleViewModel>(R.layout.fragment_schedule) {

    override val viewModel: ScheduleViewModel by viewModels()
}
