package com.haanbin.todayrecode.ui.splash

import androidx.lifecycle.viewModelScope
import com.haanbin.todayrecode.base.BaseViewModel
import com.haanbin.todayrecode.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    init {
        viewModelScope.launch {
            delay(1000L)
            navDirections.value =
                Event(SplashFragmentDirections.actionSplashFragmentToMainFragment())
        }
    }
}