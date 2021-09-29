package com.haanbin.todayrecode.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.haanbin.todayrecode.util.Event

abstract class BaseViewModel : ViewModel() {

    val showToast = MutableLiveData<Event<String>>()
    val showToastStringRes = MutableLiveData<Event<Int>>()
    val navDirections = MutableLiveData<Event<NavDirections>>()
}
