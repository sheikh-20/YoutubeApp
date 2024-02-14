package com.application.youtubeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(): ViewModel() {

    private var _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    init {
        viewModelScope.launch {
            delay(3_000L)
            _loading.value = false
        }
    }
}