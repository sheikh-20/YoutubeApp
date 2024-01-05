package com.application.youtubeapp.data.mappers

import com.application.youtubeapp.data.response.PopularVideoDto
import com.application.youtubeapp.domain.model.PopularVideo

fun PopularVideoDto.Item.toDomain(): PopularVideo.Item {
    return PopularVideo.Item(
        kind = this.kind,
        id = this.id,
        etag = this.etag,
        snippet = PopularVideo.Item.Snippet(
            categoryId = this.snippet?.categoryId,
            channelId = this.snippet?.channelId,
            channelTitle = this.snippet?.channelTitle,
//                    defaultAudioLanguage = it?.snippet?.defaultAudioLanguage,
//                    defaultLanguage = it?.snippet?.defaultLanguage,
            description = this.snippet?.description,
            liveBroadcastContent = this.snippet?.liveBroadcastContent,
            localized = PopularVideo.Item.Snippet.Localized(
                description = this.snippet?.localized?.description,
                title = this.snippet?.localized?.title
            ),
            publishedAt = this.snippet?.publishedAt,
//                    tags = it?.snippet?.tags,
            thumbnails = PopularVideo.Item.Snippet.Thumbnails(
                default = PopularVideo.Item.Snippet.Thumbnails.Default(
                    height = this.snippet?.thumbnails?.default?.height,
                    width = this.snippet?.thumbnails?.default?.width,
                    url = this.snippet?.thumbnails?.default?.url
                ),
                high = PopularVideo.Item.Snippet.Thumbnails.High(
                    height = this.snippet?.thumbnails?.high?.height,
                    width = this.snippet?.thumbnails?.high?.width,
                    url = this.snippet?.thumbnails?.high?.url
                ),
//                        maxres = PopularVideo.Item.Snippet.Thumbnails.Maxres(
//                            height = it?.snippet?.thumbnails?.maxres?.height,
//                            width = it?.snippet?.thumbnails?.maxres?.width,
//                            url = it?.snippet?.thumbnails?.maxres?.url
//                        ),
                medium = PopularVideo.Item.Snippet.Thumbnails.Medium(
                    height = this.snippet?.thumbnails?.medium?.height,
                    width = this.snippet?.thumbnails?.medium?.width,
                    url = this.snippet?.thumbnails?.medium?.url
                ),
                standard = PopularVideo.Item.Snippet.Thumbnails.Standard(
                    height = this.snippet?.thumbnails?.standard?.height,
                    width = this.snippet?.thumbnails?.standard?.width,
                    url = this.snippet?.thumbnails?.standard?.url
                ),
            ),
            title = this.snippet?.title
        )
    )
}