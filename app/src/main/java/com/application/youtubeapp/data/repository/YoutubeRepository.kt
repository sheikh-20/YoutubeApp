package com.application.youtubeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.application.youtubeapp.data.api.YoutubeApi
import com.application.youtubeapp.data.response.ChannelInfoDto
import com.application.youtubeapp.data.response.PopularVideoDto
import com.application.youtubeapp.data.response.VideoCategoryDto
import com.application.youtubeapp.data.response.VideoDetailDto
import com.application.youtubeapp.paging.VideoPopularPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface YoutubeRepository {
    suspend fun getVideoCategories(): Response<VideoCategoryDto>
    suspend fun getPopularVideo(pageToken: String = ""): Response<PopularVideoDto>

    fun getPopularVideoPagingFlow(videoCategoryId: String = ""): Flow<PagingData<PopularVideoDto.Item>>

    suspend fun getChannelInfo(channelId: String = ""): Response<ChannelInfoDto>

    suspend fun getVideoDetails(videoId: String = ""): Response<VideoDetailDto>
}

class YoutubeRepositoryImpl @Inject constructor(private val api: YoutubeApi, private val coroutineScope: CoroutineScope): YoutubeRepository {
    override suspend fun getVideoCategories(): Response<VideoCategoryDto> = api.getVideoCategory()
    override suspend fun getPopularVideo(pageToken: String): Response<PopularVideoDto> = api.getPopularVideos(pageToken = pageToken)

    override fun getPopularVideoPagingFlow(videoCategoryId: String): Flow<PagingData<PopularVideoDto.Item>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            VideoPopularPagingSource(api, coroutineScope, videoCategoryId)
        }
    ).flow

    override suspend fun getChannelInfo(channelId: String): Response<ChannelInfoDto> = api.getChannelInfo(id = channelId)

    override suspend fun getVideoDetails(videoId: String): Response<VideoDetailDto> = api.getVideoDetails(id = videoId)
}