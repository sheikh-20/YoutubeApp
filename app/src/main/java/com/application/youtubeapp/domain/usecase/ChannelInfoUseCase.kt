package com.application.youtubeapp.domain.usecase

import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.data.mappers.toDomain
import com.application.youtubeapp.data.repository.YoutubeRepository
import com.application.youtubeapp.domain.model.ChannelInfo
import timber.log.Timber
import javax.inject.Inject

interface ChannelInfoUseCase {
    suspend operator fun invoke(channelId: String = ""): Resource<ChannelInfo>
}

class GetChannelInfoInteractor @Inject constructor(private val repository: YoutubeRepository): ChannelInfoUseCase {

    private companion object {
        const val TAG = "ChannelInfoUseCase"
    }

    override suspend fun invoke(channelId: String): Resource<ChannelInfo> {
        return try {
            Resource.Loading

            val result = repository.getChannelInfo(channelId = channelId)

            if (result.isSuccessful) {
                Timber.tag(TAG).d(result.body().toString())

                val channelInfo = result.body()?.toDomain() ?: ChannelInfo(null, null, null)
                Resource.Success(data = channelInfo)
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