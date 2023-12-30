package com.application.youtubeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.domain.model.VideoCategory
import com.application.youtubeapp.domain.usecase.VideoCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val videoCategoryUseCase: VideoCategoryUseCase): ViewModel() {

    private companion object {
        const val TAG = "HomeViewModel"
    }

    private var _videoCategory = MutableLiveData<Resource<List<VideoCategory>>>(Resource.Loading)
    val videoCategory: LiveData<Resource<List<VideoCategory>>> get() = _videoCategory

    fun getVideoCategory() = viewModelScope.launch {
        try {
            _videoCategory.postValue(videoCategoryUseCase() ?: return@launch)
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception)
        }
    }
}