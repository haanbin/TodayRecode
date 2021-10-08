package com.haanbin.todayrecode.ui.main.history

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseFragment
import com.haanbin.todayrecode.databinding.FragmentHistoryBinding
import com.haanbin.todayrecode.ext.toCurrentDate
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, HistoryViewModel>(R.layout.fragment_history) {

    override val viewModel: HistoryViewModel by viewModels()

    private val historyAdapter by lazy { HistoryAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHistory.adapter = historyAdapter

        viewModel.items.observe(viewLifecycleOwner, { historyAdapter.submitList(it) })

        viewModel.showDatePicker.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                showDatePicker(it)
            }
        })

        viewModel.navDirections.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(it)
            }
        })
    }

    private fun showDatePicker(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, monthOfYear, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(selectedYear, monthOfYear, dayOfMonth)
                viewModel.onDatePickerSelected(selectedCalendar.time)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = Date().toCurrentDate().time
        datePickerDialog.show()
    }
}