package com.application.youtubeapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelInfoDto(

    @SerialName("etag")
    val etag: String? = null,

    @SerialName("items")
    val items: List<Item?>? = null,

    @SerialName("kind")
    val kind: String? = null,

    @SerialName("pageInfo")
    val pageInfo: PageInfo? = null
) {

    @Serializable
    data class Item(

        @SerialName("contentDetails")
        val contentDetails: ContentDetails? = null,

        @SerialName("etag")
        val etag: String? = null,

        @SerialName("id")
        val id: String? = null,

        @SerialName("kind")
        val kind: String? = null,

        @SerialName("snippet")
        val snippet: Snippet? = null
    ) {

        @Serializable
        data class ContentDetails(

            @SerialName("relatedPlaylists")
            val relatedPlaylists: RelatedPlaylists? = null
        ) {

            @Serializable
            data class RelatedPlaylists(

                @SerialName("likes")
                val likes: String? = null,

                @SerialName("uploads")
                val uploads: String? = null
            )
        }

        @Serializable
        data class Snippet(

            @SerialName("country")
            val country: String? = null,

            @SerialName("customUrl")
            val customUrl: String? = null,

            @SerialName("description")
            val description: String? = null,

            @SerialName("localized")
            val localized: Localized? = null,

            @SerialName("publishedAt")
            val publishedAt: String? = null,

            @SerialName("thumbnails")
            val thumbnails: Thumbnails? = null,

            @SerialName("title")
            val title: String? = null
        ) {

            @Serializable
            data class Localized(

                @SerialName("description")
                val description: String? = null,

                @SerialName("title")
                val title: String? = null
            )

            @Serializable
            data class Thumbnails(

                @SerialName("default")
                val default: Default? = null,

                @SerialName("high")
                val high: High? = null,

                @SerialName("medium")
                val medium: Medium? = null
            ) {

                @Serializable
                data class Default(

                    @SerialName("height")
                    val height: Int? = null,

                    @SerialName("url")
                    val url: String? = null,

                    @SerialName("width")
                    val width: Int? = null
                )

                @Serializable
                data class High(

                    @SerialName("height")
                    val height: Int? = null,

                    @SerialName("url")
                    val url: String? = null,

                    @SerialName("width")
                    val width: Int? = null
                )

                @Serializable
                data class Medium(

                    @SerialName("height")
                    val height: Int? = null,

                    @SerialName("url")
                    val url: String? = null,

                    @SerialName("width")
                    val width: Int? = null
                )
            }
        }
    }

    @Serializable
    data class PageInfo(

        @SerialName("resultsPerPage")
        val resultsPerPage: Int? = null,

        @SerialName("totalResults")
        val totalResults: Int? = null
    )
}