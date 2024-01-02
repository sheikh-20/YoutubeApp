package com.application.youtubeapp.domain.usecase

import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.data.mappers.toDomain
import com.application.youtubeapp.data.repository.YoutubeRepository
import com.application.youtubeapp.domain.model.PopularVideo
import com.application.youtubeapp.domain.model.VideoCategory
import timber.log.Timber

interface VideoPopularUseCase {
    suspend operator fun invoke(): Resource<PopularVideo>
}

class GetVideoPopularInteractor(private val repository: YoutubeRepository): VideoPopularUseCase {

    private companion object {
        const val TAG = "VideoPopularPopUseCase"
    }

    override suspend fun invoke(): Resource<PopularVideo> {
        return try {
            Resource.Loading

            val result = repository.getPopularVideo()
            Timber.tag(TAG).d(result.body()?.etag.toString())

            if (result.isSuccessful) {
                val popularVideo = result.body()?.toDomain() ?: PopularVideo(null, null, null, null)
                Resource.Success(data = popularVideo)
            } else if (result.code() == 400 || result.code() == 401 || result.code() == 404) {
                Timber.tag(TAG).e("404 error")
                Resource.Failure(Throwable())
            } else {
                Timber.tag(TAG).e("Server error")
                Resource.Failure(Throwable())
            }

        } catch (throwable: Throwable) {
            Timber.tag(TAG).e(throwable)
            Resource.Failure(throwable)
        }
    }
}