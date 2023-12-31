package com.application.youtubeapp.data.api

import com.application.youtubeapp.data.response.LanguageDto
import com.application.youtubeapp.data.response.VideoCategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("youtube/v3/i18nLanguages")
    suspend fun getLanguages(): Response<LanguageDto>

    @GET("youtube/v3/videoCategories")
    suspend fun getVideoCategory(@Query("part") part: String = "snippet", @Query("regionCode") regionCode: String = "IN"): Response<VideoCategoryDto>
}