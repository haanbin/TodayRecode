package com.haanbin.todayrecode.ui.main.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haanbin.todayrecode.base.BaseViewModel
import com.haanbin.todayrecode.data.entity.RecodeItem
import com.haanbin.todayrecode.data.local.entity.toRecodeItem
import com.haanbin.todayrecode.domain.GetRecodesUseCase
import com.haanbin.todayrecode.domain.GetTodayRecodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(private val getTodayRecodeUseCase: GetTodayRecodeUseCase) :
    BaseViewModel() {

    private val _todayRecodeItem = MutableLiveData<RecodeItem>()
    val todayRecodeItem: LiveData<RecodeItem> = _todayRecodeItem

    private val _emptyVisible = MutableLiveData<Boolean>(false)
    val emptyVisible: LiveData<Boolean> = _emptyVisible

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getTodayRecodeUseCase().map {
                it?.toRecodeItem()
            }.collect { recodeItem ->
                withContext(Dispatchers.Main) {
                    recodeItem?.let {
                        _todayRecodeItem.value = it
                    } ?: kotlin.run {
                        _emptyVisible.value = true
                    }
                }
            }
        }
    }
}