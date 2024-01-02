package com.application.youtubeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.domain.model.PopularVideo
import com.application.youtubeapp.domain.model.VideoCategory
import com.application.youtubeapp.domain.usecase.VideoCategoryUseCase
import com.application.youtubeapp.domain.usecase.VideoPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val videoCategoryUseCase: VideoCategoryUseCase, private val videoPopularUseCase: VideoPopularUseCase): ViewModel() {

    private companion object {
        const val TAG = "HomeViewModel"
    }

    private var _videoCategory = MutableLiveData<Resource<List<VideoCategory>>>(Resource.Loading)
    val videoCategory: LiveData<Resource<List<VideoCategory>>> get() = _videoCategory

    private var _videoPopular = MutableLiveData<Resource<PopularVideo>>(Resource.Loading)
    val videoPopular: LiveData<Resource<PopularVideo>> get() = _videoPopular

    fun getVideoCategory() = viewModelScope.launch {
        try {
            _videoCategory.postValue(videoCategoryUseCase() ?: return@launch)
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception)
        }
    }

    fun getPopularVideo() = viewModelScope.launch {
        try {
            _videoPopular.postValue(videoPopularUseCase() ?: return@launch)
            Timber.tag(TAG).d(_videoPopular.value.toString())
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception)
        }
    }

    init {
        getPopularVideo()
    }
}