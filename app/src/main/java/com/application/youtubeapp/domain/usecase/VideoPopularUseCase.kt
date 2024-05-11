package com.application.youtubeapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.data.mappers.toDomain
import com.application.youtubeapp.data.repository.YoutubeRepository
import com.application.youtubeapp.domain.model.PopularVideo
import com.application.youtubeapp.domain.model.VideoCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

interface VideoPopularUseCase {
    operator fun invoke(videoCategoryId: String = ""): Flow<PagingData<PopularVideo.Item>>
}

class GetVideoPopularInteractor(private val repository: YoutubeRepository): VideoPopularUseCase {

    private companion object {
        const val TAG = "VideoCategoryUseCase"
    }

    override fun invoke(videoCategoryId: String): Flow<PagingData<PopularVideo.Item>> = repository.getPopularVideoPagingFlow(videoCategoryId).map {
        it.map { popularVideoDto ->
            popularVideoDto.toDomain()
        }
    }
}