package com.haanbin.todayrecode.ui.main.today

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haanbin.todayrecode.R
import com.haanbin.todayrecode.base.BaseViewModel
import com.haanbin.todayrecode.data.entity.RecodeItem
import com.haanbin.todayrecode.data.entity.toRecode
import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.data.local.entity.toRecodeItem
import com.haanbin.todayrecode.domain.DeleteRecodeUseCase
import com.haanbin.todayrecode.domain.GetTodayRecodeUseCase
import com.haanbin.todayrecode.domain.InsertOrUpdateRecodeUseCase
import com.haanbin.todayrecode.ext.toCurrentDate
import com.haanbin.todayrecode.ext.toShowString
import com.haanbin.todayrecode.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val getTodayRecodeUseCase: GetTodayRecodeUseCase,
    private val insertRecodeUseCase: InsertOrUpdateRecodeUseCase,
    private val deleteRecodeUseCase: DeleteRecodeUseCase
) :
    BaseViewModel() {

    private val _todayRecodeItem = MutableLiveData<RecodeItem?>()
    val todayRecodeItem: LiveData<RecodeItem?> = _todayRecodeItem

    private val _keyboardVisible = MutableLiveData<Event<Boolean>>()
    val keyboardVisible: LiveData<Event<Boolean>> = _keyboardVisible

    private val _editVisible = MutableLiveData(false)
    val editVisible: LiveData<Boolean> = _editVisible

    private val _emptyVisible = MutableLiveData(false)
    val emptyVisible: LiveData<Boolean> = _emptyVisible

    private val _todayDate = MutableLiveData<String>()
    val todayDate: LiveData<String> = _todayDate

    val editRecodeContent = MutableLiveData("")

    init {
        _todayDate.value = Date().toShowString()

        viewModelScope.launch(Dispatchers.IO) {
            getTodayRecodeUseCase().map {
                it?.toRecodeItem()
            }.collect { recodeItem ->
                withContext(Dispatchers.Main) {
                    recodeItem?.let {
                        _todayRecodeItem.value = it
                        editRecodeContent.value = it.content
                    } ?: kotlin.run {
                        _emptyVisible.value = true
                    }
                }
            }
        }
    }

    /**
     * 기록 empty 화면 클릭
     */
    fun onEmptyClick() {
        changeEditMode()
    }

    /**
     * 기록 완료 클릭
     */
    fun onRecodeDoneClick() {
        if (editRecodeContent.value.isNullOrEmpty()) {
            showToastStringRes.value = Event(R.string.today_empty)
            return
        }
        _keyboardVisible.value = Event(false)
        _editVisible.value = false
        val recode: Recode = _todayRecodeItem.value?.let { recodeItem ->
            val recode = recodeItem.toRecode()
            recode.content = editRecodeContent.value ?: ""
            recode
        } ?: kotlin.run {
            Recode(
                editRecodeContent.value ?: "",
                Date().toCurrentDate()
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            insertRecodeUseCase(recode)
        }
    }

    /**
     * 키보드 완료 이벤트
     */
    fun onEditorAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onRecodeDoneClick()
            return true
        }
        return false
    }

    /**
     * 취소 클릭
     */
    fun cancelClick() {
        changeEditMode(false)
    }

    /**
     * 수정 클릭
     */
    fun modifyClick() {
        changeEditMode()
    }

    /**
     * 삭제 클릭
     */
    fun deleteClick() {
        navDirections.value = Event(TodayFragmentDirections.actionTodayFragmentToDeleteRecodeDialogFragment())
    }

    fun deleteRecode() {
        viewModelScope.launch(Dispatchers.IO) {
            _todayRecodeItem.value?.let { recodeItem ->
                val recode = recodeItem.toRecode()
                recode.content = editRecodeContent.value ?: ""
                deleteRecodeUseCase(recode)
                withContext(Dispatchers.Main) {
                    _emptyVisible.value = true
                    editRecodeContent.value = null
                    _todayRecodeItem.value = null
                }
            }
        }
    }

    private fun changeEditMode(isEdit: Boolean = true) {
        _keyboardVisible.value = Event(isEdit)
        _editVisible.value = isEdit
        _emptyVisible.value = !isEdit && _todayRecodeItem.value == null
    }
}