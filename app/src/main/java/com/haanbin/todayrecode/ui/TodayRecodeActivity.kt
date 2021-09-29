package com.haanbin.todayrecode.ui

import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseActivity
import com.haanbin.todayrecode.databinding.ActivityTodayRecodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayRecodeActivity :
    BaseActivity<ActivityTodayRecodeBinding, TodayRecodeViewModel>(R.layout.activity_today_recode) {

    override val viewModel: TodayRecodeViewModel by viewModels()

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.today_recode_nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}
