package com.haanbin.todayrecode.ui.main.history

import androidx.fragment.app.viewModels
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, HistoryViewModel>(R.layout.fragment_history) {

    override val viewModel: HistoryViewModel by viewModels()
}