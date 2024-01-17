package com.application.youtubeapp.domain.usecase

import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.data.mappers.toDomain
import com.application.youtubeapp.data.repository.YoutubeRepository
import com.application.youtubeapp.domain.model.VideoDetail
import timber.log.Timber
import javax.inject.Inject

interface VideoDetailUseCase {
    suspend operator fun invoke(videoId: String = ""): Resource<VideoDetail>
}

class GetVideoDetailInteractor @Inject constructor(private val repository: YoutubeRepository): VideoDetailUseCase {

    private companion object {
        const val TAG = "VideoDetailUseCase"
    }

    override suspend fun invoke(videoId: String): Resource<VideoDetail> {
        return try {
            Resource.Loading

            val result = repository.getVideoDetails(videoId)

            if (result.isSuccessful) {
                Timber.tag(TAG).d(result.body().toString())

                val videoDetail = result.body()?.toDomain() ?: VideoDetail(null, null, null)
                Resource.Success(data = videoDetail)
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