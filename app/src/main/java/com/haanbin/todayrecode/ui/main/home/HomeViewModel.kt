package com.haanbin.todayrecode.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haanbin.todayrecode.base.BaseViewModel
import com.haanbin.todayrecode.data.entity.RecodeItem
import com.haanbin.todayrecode.data.local.entity.toRecodeItem
import com.haanbin.todayrecode.domain.GetFirstRecodeUseCase
import com.haanbin.todayrecode.domain.GetRecentRecodeUserCase
import com.haanbin.todayrecode.domain.GetTodayRecodeUseCase
import com.haanbin.todayrecode.ext.toCurrentDate
import com.haanbin.todayrecode.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFirstRecodeUseCase: GetFirstRecodeUseCase,
    private val getRecentRecodeUserCase: GetRecentRecodeUserCase,
    private val getTodayRecodeUseCase: GetTodayRecodeUseCase
) : BaseViewModel() {

    private val _recentRecode = MutableLiveData<RecodeItem>()
    val recentRecode: LiveData<RecodeItem> = _recentRecode

    private val _todayDate = MutableLiveData<String>()
    val todayDate: LiveData<String> = _todayDate

    private val _todayRecodeVisible = MutableLiveData(false)
    val todayRecodeVisible: LiveData<Boolean> = _todayRecodeVisible

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getFirstRecodeUseCase().collect { recode ->
                var todayText = ""
                recode?.let {
                    val recodeDate = it.inputDate
                    val nowDate = Date().toCurrentDate()
                    val dateDiff = nowDate.time - recodeDate.time
                    Timber.d("dateDiff : $dateDiff")
                    val calDateDays = abs(dateDiff / (24 * 60 * 60 * 1000))
                    Timber.d("calDateDays : $calDateDays")
                    todayText = if (calDateDays == 0L) {
                        "오늘부터 기록 시작!"
                    } else {
                        "오늘을 기록한지 $calDateDays 일째!"
                    }
                } ?: kotlin.run {
                    todayText = "오늘부터 기록 시작!"
                }
                withContext(Dispatchers.Main) {
                    _todayDate.value = todayText
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            getRecentRecodeUserCase().map {
                it?.toRecodeItem()
            }.collect { recodeItem ->
                withContext(Dispatchers.Main) {
                    recodeItem?.let {
                        _recentRecode.value = it
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            getTodayRecodeUseCase().map {
                it?.toRecodeItem()
            }.collect { recodeItem ->
                withContext(Dispatchers.Main) {
                    recodeItem?.let {
                        _todayRecodeVisible.value = false
                    } ?: kotlin.run {
                        _todayRecodeVisible.value = true
                    }
                }
            }
        }

    }

    /**
     * 최근작성 기록 없음 클릭
     */
    fun onRecentRecodeClick() {
        goTodayFragment()
    }

    /**
     * 타임스캐줄 없음 클릭
     */
    fun onTimeScheduleClick() {
        goScheduleFragment()
    }

    /**
     * 오늘기록 없음 클릭
     */
    fun onTodayRecodeClick() {
        goTodayFragment()
    }

    private fun goTodayFragment() {
        navDirections.value = Event(HomeFragmentDirections.actionHomeFragmentToTodayFragment())
    }

    private fun goScheduleFragment() {
        navDirections.value = Event(HomeFragmentDirections.actionHomeFragmentToScheduleFragment())

    }
}