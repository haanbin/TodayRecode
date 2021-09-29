package com.haanbin.todayrecode.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.haanbin.todayrecode.BR
import com.haanbin.todayrecode.ext.showToast

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    private val layoutResId: Int
) : Fragment() {

    protected lateinit var binding: B
    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        with(binding) {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBaseObserve()
    }

    private fun onBaseObserve() {
        with(viewModel) {
            showToast.observe(
                viewLifecycleOwner,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        showToast(it)
                    }
                }
            )
            showToastStringRes.observe(
                viewLifecycleOwner,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        showToast(getString(it))
                    }
                }
            )
        }
    }

    private fun showToast(msg: String) {
        requireContext().showToast(msg)
    }
}