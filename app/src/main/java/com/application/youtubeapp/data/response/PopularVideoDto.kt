package com.application.youtubeapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularVideoDto(

    @SerialName("etag")
    val etag: String?,

    @SerialName("items")
    val items: List<Item?>?,

    @SerialName("kind")
    val kind: String?,

    @SerialName("nextPageToken")
    val nextPageToken: String?,

    @SerialName("pageInfo")
    val pageInfo: PageInfo?
) {

    @Serializable
    data class Item(

        @SerialName("etag")
        val etag: String?,

        @SerialName("id")
        val id: String?,

        @SerialName("kind")
        val kind: String?,

        @SerialName("snippet")
        val snippet: Snippet?
    ) {

        @Serializable
        data class Snippet(

            @SerialName("categoryId")
            val categoryId: String?,

            @SerialName("channelId")
            val channelId: String?,

            @SerialName("channelTitle")
            val channelTitle: String?,

//            @SerialName("defaultAudioLanguage")
//            val defaultAudioLanguage: String?,

//            @SerialName("defaultLanguage")
//            val defaultLanguage: String?,

            @SerialName("description")
            val description: String?,

            @SerialName("liveBroadcastContent")
            val liveBroadcastContent: String?,

            @SerialName("localized")
            val localized: Localized?,

            @SerialName("publishedAt")
            val publishedAt: String?,

//            @SerialName("tags")
//            val tags: List<String?>?,

            @SerialName("thumbnails")
            val thumbnails: Thumbnails?,

            @SerialName("title")
            val title: String?
        ) {

            @Serializable
            data class Localized(
                @SerialName("description")
                val description: String?,

                @SerialName("title")
                val title: String?
            )

            @Serializable
            data class Thumbnails(

                @SerialName("default")
                val default: Default?,

                @SerialName("high")
                val high: High?,

                @SerialName("maxres")
                val maxres: Maxres?,

                @SerialName("medium")
                val medium: Medium?,

                @SerialName("standard")
                val standard: Standard?
            ) {

                @Serializable
                data class Default(

                    @SerialName("height")
                    val height: Int?,

                    @SerialName("url")
                    val url: String?,

                    @SerialName("width")
                    val width: Int?
                )

                @Serializable
                data class High(

                    @SerialName("height")
                    val height: Int?,

                    @SerialName("url")
                    val url: String?,

                    @SerialName("width")
                    val width: Int?
                )

                @Serializable
                data class Maxres(

                    @SerialName("height")
                    val height: Int?,

                    @SerialName("url")
                    val url: String?,

                    @SerialName("width")
                    val width: Int?
                )

                @Serializable
                data class Medium(

                    @SerialName("height")
                    val height: Int?,

                    @SerialName("url")
                    val url: String?,

                    @SerialName("width")
                    val width: Int?
                )

                @Serializable
                data class Standard(
                    @SerialName("height")
                    val height: Int?,

                    @SerialName("url")
                    val url: String?,

                    @SerialName("width")
                    val width: Int?
                )
            }
        }
    }

    @Serializable
    data class PageInfo(

        @SerialName("resultsPerPage")
        val resultsPerPage: Int?,

        @SerialName("totalResults")
        val totalResults: Int?
    )
}