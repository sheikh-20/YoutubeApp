package com.application.youtubeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.domain.model.VideoDetail
import com.application.youtubeapp.domain.usecase.VideoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val videoDetailUseCase: VideoDetailUseCase): ViewModel(){

    companion object {
        private const val TAG = "DetailViewModel"
    }

    private var _videoDetail = MutableLiveData<Resource<VideoDetail>>(Resource.Loading)
    val videoDetail: LiveData<Resource<VideoDetail>> get() = _videoDetail

    fun getVideoDetails(videoId: String) = viewModelScope.launch {
        try {
            _videoDetail.postValue(videoDetailUseCase(videoId) ?: return@launch)
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception)
        }
    }
}