package com.application.youtubeapp.domain.usecase

import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.data.mappers.toDomain
import com.application.youtubeapp.data.repository.YoutubeRepository
import com.application.youtubeapp.domain.model.ChannelInfo
import com.application.youtubeapp.domain.model.VideoDetail
import com.application.youtubeapp.domain.model.VideoDetailWithInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

interface VideoDetailWithInfoUseCase {
    suspend operator fun invoke(videoId: String = ""): Resource<VideoDetailWithInfo>
}

class GetVideoDetailWithInfoInteractor @Inject constructor(private val repository: YoutubeRepository): VideoDetailWithInfoUseCase {

    private companion object {
        const val TAG = "VideoDetailWithInfoUseCase"
    }

    override suspend fun invoke(videoId: String): Resource<VideoDetailWithInfo> {
        return try {
            Resource.Loading

            withContext(Dispatchers.Default) {
                val videoDetail = async { repository.getVideoDetails(videoId) }.await()
                val videoChannel = async { repository.getChannelInfo(videoDetail.body()?.items?.get(0)?.snippet?.channelId ?: "") }.await()

                if (videoDetail.isSuccessful && videoChannel.isSuccessful) {
                    Timber.tag(TAG).d(videoDetail.body().toString())

                    Resource.Success(data = VideoDetailWithInfo(videoDetail = videoDetail.body()?.toDomain() ?: VideoDetail(null, null ,null) , channelInfo = videoChannel.body()?.toDomain() ?: ChannelInfo(null, null, null)))
                } else if (videoDetail.code() == 400 || videoDetail.code() == 401 || videoDetail.code() == 404) {
                    Timber.tag(TAG).e("404 error")
                    Resource.Failure(Throwable())
                } else if (videoChannel.code() == 400 || videoChannel.code() == 401 || videoChannel.code() == 404) {
                    Timber.tag(TAG).e("404 error")
                    Resource.Failure(Throwable())
                }else {
                    Timber.tag(TAG).e("Server error")
                    Resource.Failure(Throwable())
                }
            }
        } catch (throwable: Throwable) {
            Timber.tag(TAG).e(throwable)
            Resource.Failure(throwable)
        }
    }
}