package com.application.youtubeapp.domain.usecase

import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.data.mappers.toDomain
import com.application.youtubeapp.data.repository.YoutubeRepository
import com.application.youtubeapp.domain.model.VideoCategory
import timber.log.Timber

interface VideoCategoryUseCase {
    suspend operator fun invoke(): Resource<List<VideoCategory>>
}

class GetVideoCategoryInteractor(private val repository: YoutubeRepository): VideoCategoryUseCase {

    private companion object {
        const val TAG = "VideoCategoryUseCase"
    }

    override suspend fun invoke(): Resource<List<VideoCategory>> {
        return try {
            Resource.Loading

            val result = repository.getVideoCategories()
            Timber.tag(TAG).d(result.body()?.etag.toString())

            if (result.isSuccessful) {
                val category = result.body()?.toDomain() ?: emptyList()
                Resource.Success(data = category)
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