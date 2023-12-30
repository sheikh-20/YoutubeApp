package com.application.youtubeapp.data.mappers

import com.application.youtubeapp.data.response.VideoCategoryDto
import com.application.youtubeapp.domain.model.VideoCategory

fun VideoCategoryDto.toDomain(): List<VideoCategory> {
    return this.items?.map {
        VideoCategory(
            etag = it?.etag,
            id = it?.id,
            kind = it?.kind,
            snippet = VideoCategory.Snippet(
                assignable = it?.snippet?.assignable,
                channelId = it?.snippet?.channelId,
                title = it?.snippet?.title
            )
        )
    } ?: emptyList()
}