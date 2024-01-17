package com.application.youtubeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.domain.model.VideoDetailWithInfo
import com.application.youtubeapp.domain.usecase.VideoDetailUseCase
import com.application.youtubeapp.domain.usecase.VideoDetailWithInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val videoDetailWithInfoUseCase: VideoDetailWithInfoUseCase): ViewModel(){

    companion object {
        private const val TAG = "DetailViewModel"
    }

    private var _videoDetailWithInfo = MutableLiveData<Resource<VideoDetailWithInfo>>(Resource.Loading)
    val videoDetailWithInfo: LiveData<Resource<VideoDetailWithInfo>> get() = _videoDetailWithInfo

    fun getVideoDetailsWithInfo(videoId: String) = viewModelScope.launch {
        try {
            _videoDetailWithInfo.postValue(videoDetailWithInfoUseCase(videoId) ?: return@launch)
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception)
        }
    }
}