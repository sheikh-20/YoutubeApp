package com.application.youtubeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.application.youtubeapp.common.Resource
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

    private var _videoCategoryResponse = MutableLiveData<Resource<List<VideoCategory>>>(Resource.Loading)
    val videoCategoryResponse: LiveData<Resource<List<VideoCategory>>> get() = _videoCategoryResponse

    private var _selectedCategory = MutableLiveData<String>("")
    val selectedCategory: LiveData<String> get() = _selectedCategory

    fun getVideoCategory() = viewModelScope.launch {
        try {
            _videoCategoryResponse.postValue(videoCategoryUseCase() ?: return@launch)
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception)
        }
    }

    fun getPopularVideo(videoCategoryId: String = "") = videoPopularUseCase(videoCategoryId).cachedIn(viewModelScope)

    fun categoryClick(categoryId: String) {
        _selectedCategory.value = categoryId
    }

}