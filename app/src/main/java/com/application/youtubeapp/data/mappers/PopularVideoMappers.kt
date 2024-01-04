package com.application.youtubeapp.data.mappers

import com.application.youtubeapp.data.response.PopularVideoDto
import com.application.youtubeapp.domain.model.PopularVideo

fun PopularVideoDto.toDomain(): PopularVideo {
    return PopularVideo(
        items = this.items?.map {
            PopularVideo.Item(
                kind = it?.kind,
                id = it?.id,
                etag = it?.etag,
                snippet = PopularVideo.Item.Snippet(
                    categoryId = it?.snippet?.categoryId,
                    channelId = it?.snippet?.channelId,
                    channelTitle = it?.snippet?.channelTitle,
//                    defaultAudioLanguage = it?.snippet?.defaultAudioLanguage,
//                    defaultLanguage = it?.snippet?.defaultLanguage,
                    description = it?.snippet?.description,
                    liveBroadcastContent = it?.snippet?.liveBroadcastContent,
                    localized = PopularVideo.Item.Snippet.Localized(
                        description = it?.snippet?.localized?.description,
                        title = it?.snippet?.localized?.title
                    ),
                    publishedAt = it?.snippet?.publishedAt,
//                    tags = it?.snippet?.tags,
                    thumbnails = PopularVideo.Item.Snippet.Thumbnails(
                        default = PopularVideo.Item.Snippet.Thumbnails.Default(
                            height = it?.snippet?.thumbnails?.default?.height,
                            width = it?.snippet?.thumbnails?.default?.width,
                            url = it?.snippet?.thumbnails?.default?.url
                        ),
                        high = PopularVideo.Item.Snippet.Thumbnails.High(
                            height = it?.snippet?.thumbnails?.high?.height,
                            width = it?.snippet?.thumbnails?.high?.width,
                            url = it?.snippet?.thumbnails?.high?.url
                        ),
                        maxres = PopularVideo.Item.Snippet.Thumbnails.Maxres(
                            height = it?.snippet?.thumbnails?.maxres?.height,
                            width = it?.snippet?.thumbnails?.maxres?.width,
                            url = it?.snippet?.thumbnails?.maxres?.url
                        ),
                        medium = PopularVideo.Item.Snippet.Thumbnails.Medium(
                            height = it?.snippet?.thumbnails?.medium?.height,
                            width = it?.snippet?.thumbnails?.medium?.width,
                            url = it?.snippet?.thumbnails?.medium?.url
                        ),
                        standard = PopularVideo.Item.Snippet.Thumbnails.Standard(
                            height = it?.snippet?.thumbnails?.standard?.height,
                            width = it?.snippet?.thumbnails?.standard?.width,
                            url = it?.snippet?.thumbnails?.standard?.url
                        ),
                    ),
                    title = it?.snippet?.title
                )
            )
        },
        kind = this.kind,
        nextPageToken = this.nextPageToken,
        pageInfo = PopularVideo.PageInfo(
            resultsPerPage = this.pageInfo?.resultsPerPage,
            totalResults = this.pageInfo?.totalResults
        )
    )
}