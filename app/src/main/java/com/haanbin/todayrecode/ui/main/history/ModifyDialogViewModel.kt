package com.haanbin.todayrecode.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haanbin.todayrecode.base.BaseViewModel
import com.haanbin.todayrecode.data.entity.RecodeItem
import com.haanbin.todayrecode.data.entity.toRecode
import com.haanbin.todayrecode.domain.InsertOrUpdateRecodeUseCase
import com.haanbin.todayrecode.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyDialogViewModel @Inject constructor(private val insertRecodeUseCase: InsertOrUpdateRecodeUseCase) :
    BaseViewModel() {

    private val _recodeItem = MutableLiveData<RecodeItem>()
    val recodeItem: LiveData<RecodeItem> = _recodeItem

    private val _dismissDialog = MutableLiveData<Event<Unit>>()
    val dismissDialog: LiveData<Event<Unit>> = _dismissDialog

    val content = MutableLiveData<String>()

    fun setArgument(argument: ModifyDialogFragmentArgs) {
        val recodeItem = argument.recodeItem
        _recodeItem.value = recodeItem
        content.value = recodeItem.content
    }

    fun confirmClick() {
        updateItem()
        dismissDialog()
    }

    fun cancelClick() {
        dismissDialog()
    }

    private fun updateItem() {
        _recodeItem.value?.let {
            val recode = it.toRecode()
            recode.content = this.content.value ?: ""
            viewModelScope.launch(Dispatchers.IO) {
                insertRecodeUseCase(recode)
            }
        }
    }

    private fun dismissDialog() {
        _dismissDialog.value = Event(Unit)
    }

}