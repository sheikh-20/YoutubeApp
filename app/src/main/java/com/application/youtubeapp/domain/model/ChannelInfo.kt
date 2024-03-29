package com.application.youtubeapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ChannelInfo(
    val etag: String?,
    val items: List<Item?>?,
    val kind: String?,
) {
    data class Item(
        val contentDetails: ContentDetails?,
        val statistics: Statistics?,
        val etag: String?,
        val id: String?,
        val kind: String?,
        val snippet: Snippet?
    ) {
        data class ContentDetails(
            val relatedPlaylists: RelatedPlaylists?
        ) {
            data class RelatedPlaylists(
                val likes: String?,
                val uploads: String?
            )
        }

        data class Statistics(
            val viewCount: String? = null,
            val subscriberCount: String? = null,
            val hiddenSubscriberCount: Boolean? = null,
            val videoCount: String? = null
        )

        data class Snippet(
            val country: String?,
            val customUrl: String?,
            val description: String?,
            val localized: Localized?,
            val publishedAt: String?,
            val thumbnails: Thumbnails?,
            val title: String?
        ) {
            data class Localized(
                val description: String?,
                val title: String?
            )

            data class Thumbnails(
                val default: Default?,
                val high: High?,
                val medium: Medium?
            ) {
                data class Default(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )

                data class High(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )

                data class Medium(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )
            }
        }
    }
}