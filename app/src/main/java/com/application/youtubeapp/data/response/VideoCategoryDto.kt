package com.application.youtubeapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoCategoryDto(

    @SerialName("etag")
    val etag: String?,

    @SerialName("items")
    val items: List<Item?>?,

    @SerialName("kind")
    val kind: String?
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

            @SerialName("assignable")
            val assignable: Boolean?,

            @SerialName("channelId")
            val channelId: String?,

            @SerialName("title")
            val title: String?
        )
    }
}