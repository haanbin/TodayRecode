package com.haanbin.todayrecode.ui.main.today

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseDialogFragment
import com.haanbin.todayrecode.databinding.DialogDeleteRecodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteRecodeDialogFragment :
    BaseDialogFragment<DialogDeleteRecodeBinding>(R.layout.dialog_delete_recode) {

    val viewModel: TodayViewModel by viewModels(
        ownerProducer = { requireParentFragment().childFragmentManager.primaryNavigationFragment!! }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDeleteRecodeConfirm.setOnClickListener {
            viewModel.deleteRecode()
            dismiss()
        }
        binding.tvDeleteRecodeCancel.setOnClickListener {
            dismiss()
        }
    }
}