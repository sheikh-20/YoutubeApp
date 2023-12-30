package com.application.youtubeapp.domain.model

data class VideoCategory(
    val etag: String?,
    val id: String?,
    val kind: String?,
    val snippet: Snippet?
) {
    data class Snippet(
        val assignable: Boolean?,
        val channelId: String?,
        val title: String?
    )
}
