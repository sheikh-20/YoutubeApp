package com.application.youtubeapp.data.repository

import com.application.youtubeapp.data.api.YoutubeApi
import com.application.youtubeapp.data.response.VideoCategoryDto
import retrofit2.Response
import javax.inject.Inject

interface YoutubeRepository {
    suspend fun getVideoCategories(): Response<VideoCategoryDto>
}

class YoutubeRepositoryImpl @Inject constructor(private val api: YoutubeApi): YoutubeRepository {
    override suspend fun getVideoCategories(): Response<VideoCategoryDto> = api.getVideoCategory()
}