package com.application.youtubeapp.data.mappers

import com.application.youtubeapp.data.response.VideoDetailDto
import com.application.youtubeapp.domain.model.VideoDetail

fun VideoDetailDto.toDomain(): VideoDetail {
    return VideoDetail(
        etag = this.etag,
        kind = this.kind,
        items = this.items?.map {
            VideoDetail.Item(
                id = it?.id,
                etag = it?.etag,
                kind = it?.kind,
                contentDetails = VideoDetail.Item.ContentDetails(
                    caption = it?.contentDetails?.caption,
                    contentRating = VideoDetail.Item.ContentDetails.ContentRating(
                        it?.contentDetails?.contentRating?.ytRating
                    ),
                    definition = it?.contentDetails?.definition,
                    dimension = it?.contentDetails?.dimension,
                    duration = it?.contentDetails?.duration,
                    licensedContent = it?.contentDetails?.licensedContent,
                    projection = it?.contentDetails?.projection
                ),
                statistics = VideoDetail.Item.Statistics(
                    viewCount = it?.statistics?.viewCount,
                    likeCount = it?.statistics?.likeCount,
                    favoriteCount = it?.statistics?.favoriteCount,
                    commentCount = it?.statistics?.commentCount
                ),
                snippet = VideoDetail.Item.Snippet(
                    categoryId = it?.snippet?.categoryId,
                    channelId = it?.snippet?.channelId,
                    channelTitle = it?.snippet?.channelTitle,
                    defaultAudioLanguage = it?.snippet?.defaultAudioLanguage,
                    defaultLanguage = it?.snippet?.defaultLanguage,
                    description = it?.snippet?.description,
                    liveBroadcastContent = it?.snippet?.liveBroadcastContent,
                    localized = VideoDetail.Item.Snippet.Localized(
                        description = it?.snippet?.localized?.description,
                        title = it?.snippet?.localized?.title
                    ),
                    publishedAt = it?.snippet?.publishedAt,
                    tags = it?.snippet?.tags,
                    thumbnails = VideoDetail.Item.Snippet.Thumbnails(
                        default = VideoDetail.Item.Snippet.Thumbnails.Default(
                            height = it?.snippet?.thumbnails?.default?.height,
                            width = it?.snippet?.thumbnails?.default?.width,
                            url = it?.snippet?.thumbnails?.default?.url
                        ),
                        high = VideoDetail.Item.Snippet.Thumbnails.High(
                            height = it?.snippet?.thumbnails?.high?.height,
                            width = it?.snippet?.thumbnails?.high?.width,
                            url = it?.snippet?.thumbnails?.high?.url
                        ),
                        maxres = VideoDetail.Item.Snippet.Thumbnails.Maxres(
                            height = it?.snippet?.thumbnails?.maxres?.height,
                            width = it?.snippet?.thumbnails?.maxres?.width,
                            url = it?.snippet?.thumbnails?.maxres?.url
                        ),
                        medium = VideoDetail.Item.Snippet.Thumbnails.Medium(
                            height = it?.snippet?.thumbnails?.medium?.height,
                            width = it?.snippet?.thumbnails?.medium?.width,
                            url = it?.snippet?.thumbnails?.medium?.url
                        ),
                        standard = VideoDetail.Item.Snippet.Thumbnails.Standard(
                            height = it?.snippet?.thumbnails?.standard?.height,
                            width = it?.snippet?.thumbnails?.standard?.width,
                            url = it?.snippet?.thumbnails?.standard?.url
                        ),
                    ),
                    title = it?.snippet?.title
                )
            )
        }
    )
}