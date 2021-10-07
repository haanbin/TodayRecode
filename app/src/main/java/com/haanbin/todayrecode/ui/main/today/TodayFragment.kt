package com.haanbin.todayrecode.ui.main.today

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentTodayBinding
import com.haanbin.todayrecode.ext.hideKeyboard
import com.haanbin.todayrecode.ext.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : BaseFragment<FragmentTodayBinding, TodayViewModel>(R.layout.fragment_today) {

    override val viewModel: TodayViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.keyboardVisible.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    binding.etTodayRecode.showKeyboard()
                } else {
                    binding.etTodayRecode.hideKeyboard()
                }
            }
        })
        viewModel.navDirections.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(it)
            }
        })
    }
}