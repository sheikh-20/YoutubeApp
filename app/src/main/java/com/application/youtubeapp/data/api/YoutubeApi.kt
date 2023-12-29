package com.application.youtubeapp.data.api

import com.application.youtubeapp.data.response.LanguageDto
import retrofit2.Response
import retrofit2.http.GET

interface YoutubeApi {

    @GET("youtube/v3/i18nLanguages")
    suspend fun getLanguages(): Response<LanguageDto>
}