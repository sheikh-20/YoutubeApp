package com.application.youtubeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.application.youtubeapp.data.api.YoutubeApi
import com.application.youtubeapp.data.response.PopularVideoDto
import com.application.youtubeapp.data.response.VideoCategoryDto
import com.application.youtubeapp.paging.VideoPopularPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface YoutubeRepository {
    suspend fun getVideoCategories(): Response<VideoCategoryDto>
    suspend fun getPopularVideo(pageToken: String = ""): Response<PopularVideoDto>

    fun getPopularVideoPagingFlow(): Flow<PagingData<PopularVideoDto.Item>>
}

class YoutubeRepositoryImpl @Inject constructor(private val api: YoutubeApi): YoutubeRepository {
    override suspend fun getVideoCategories(): Response<VideoCategoryDto> = api.getVideoCategory()
    override suspend fun getPopularVideo(pageToken: String): Response<PopularVideoDto> = api.getPopularVideos(pageToken)

    override fun getPopularVideoPagingFlow(): Flow<PagingData<PopularVideoDto.Item>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            VideoPopularPagingSource(api)
        }
    ).flow
}