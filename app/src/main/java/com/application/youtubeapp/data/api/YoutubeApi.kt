package com.application.youtubeapp.data.api

import com.application.youtubeapp.data.response.ChannelInfoDto
import com.application.youtubeapp.data.response.LanguageDto
import com.application.youtubeapp.data.response.PopularVideoDto
import com.application.youtubeapp.data.response.VideoCategoryDto
import com.application.youtubeapp.data.response.VideoDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("youtube/v3/i18nLanguages")
    suspend fun getLanguages(): Response<LanguageDto>

    @GET("youtube/v3/videoCategories")
    suspend fun getVideoCategory(@Query("part") part: String = "snippet",
                                 @Query("regionCode") regionCode: String = "IN"): Response<VideoCategoryDto>


    @GET("youtube/v3/search")
    suspend fun getPopularVideos(@Query("part") vararg part: String = arrayOf("snippet"),
                                 @Query("type") type: String = "video",
                                 @Query("q") query: String = "",
//                                 @Query("chart") chart: String = "mostPopular",
                                 @Query("videoCategoryId") videoCategoryId: String = "",
                                 @Query("maxResults") maxResults: Int = 25,
                                 @Query("regionCode") regionCode: String = "US",
                                 @Query("pageToken") pageToken: String = ""): Response<PopularVideoDto>


    @GET("youtube/v3/channels")
    suspend fun getChannelInfo(@Query("part") vararg part: String = arrayOf("snippet", "contentDetails", "statistics"),
                               @Query("id") id: String = ""): Response<ChannelInfoDto>


    @GET("youtube/v3/videos")
    suspend fun getVideoDetails(@Query("part") vararg part: String = arrayOf("snippet", "contentDetails", "statistics"),
                                @Query("id") id: String = ""): Response<VideoDetailDto>
}