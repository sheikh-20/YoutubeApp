package com.application.youtubeapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageDto(
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
        val kind: String?
    )
}