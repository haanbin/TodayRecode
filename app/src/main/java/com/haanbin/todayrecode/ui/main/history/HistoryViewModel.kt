package com.haanbin.todayrecode.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haanbin.todayrecode.base.BaseViewModel
import com.haanbin.todayrecode.data.entity.RecodeItem
import com.haanbin.todayrecode.data.local.entity.toRecodeItems
import com.haanbin.todayrecode.domain.GetRecodesUseCase
import com.haanbin.todayrecode.ext.removeTime
import com.haanbin.todayrecode.ext.toCurrentDate
import com.haanbin.todayrecode.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val getRecodesUseCase: GetRecodesUseCase) :
    BaseViewModel() {

    private val _items = MutableLiveData<List<RecodeItem>>()
    val items: LiveData<List<RecodeItem>> = _items

    private val _emptyVisible = MutableLiveData(false)
    val emptyVisible: LiveData<Boolean> = _emptyVisible

    private val _showDatePicker = MutableLiveData<Event<Date>>()
    val showDatePicker: LiveData<Event<Date>> = _showDatePicker

    private var job: Job? = null
    
    private var startDate: Date = Date().toCurrentDate()

    init {
        getHistoryItems()
    }

    fun modifyItemClick(recodeItem: RecodeItem) {
        navDirections.value =
            Event(HistoryFragmentDirections.actionHistoryFragmentToModifyDialogFragment(recodeItem.copy()))
    }

    fun calendarClick() {
        _showDatePicker.value = Event(startDate)
    }

    fun onDatePickerSelected(date: Date) {
        startDate = date.removeTime()
        job?.cancel()
        getHistoryItems()
    }

    private fun getHistoryItems() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getRecodesUseCase(DESC, startDate).map {
                it.toRecodeItems()
            }.collect { items ->
                withContext(Dispatchers.Main) {
                    _emptyVisible.value = items.isEmpty()
                    _items.value = items
                }
            }
        }
    }

    companion object {
        private const val DESC = false
    }
}