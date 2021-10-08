package com.haanbin.todayrecode.ui.main.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseDialogFragment
import com.haanbin.todayrecode.databinding.DialogModifyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModifyDialogFragment : BaseDialogFragment<DialogModifyBinding>(R.layout.dialog_modify) {

    private val viewModel: ModifyDialogViewModel by viewModels()
    private val args by navArgs<ModifyDialogFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.setArgument(args)

        viewModel.dismissDialog.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                dismiss()
            }
        })
    }
}