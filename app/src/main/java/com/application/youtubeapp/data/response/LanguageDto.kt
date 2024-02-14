package com.application.youtubeapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageDto(
    @SerialName("etag")
    val etag: String? = null,

    @SerialName("items")
    val items: List<Item?>? = null,

    @SerialName("kind")
    val kind: String? = null
) {

    @Serializable
    data class Item(
        @SerialName("etag")
        val etag: String? = null,

        @SerialName("id")
        val id: String? = null,

        @SerialName("kind")
        val kind: String? = null
    )
}