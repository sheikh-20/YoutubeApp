package com.application.youtubeapp.data.mappers

import com.application.youtubeapp.data.response.ChannelInfoDto
import com.application.youtubeapp.domain.model.ChannelInfo
import timber.log.Timber

fun ChannelInfoDto.toDomain(): ChannelInfo {

    Timber.d(this.items.toString())

    return ChannelInfo(
       etag = this.etag,
        items = this.items?.map {
            ChannelInfo.Item(
                contentDetails = ChannelInfo.Item.ContentDetails(
                    relatedPlaylists = ChannelInfo.Item.ContentDetails.RelatedPlaylists(
                        likes = it?.contentDetails?.relatedPlaylists?.likes,
                        uploads = it?.contentDetails?.relatedPlaylists?.uploads
                    )
                ),
                statistics = ChannelInfo.Item.Statistics(
                    viewCount = it?.statistics?.viewCount,
                    subscriberCount = it?.statistics?.subscriberCount,
                    hiddenSubscriberCount = it?.statistics?.hiddenSubscriberCount,
                    videoCount = it?.statistics?.videoCount
                ),
                id = it?.id,
                kind = it?.kind,
                etag = it?.etag,
                snippet = ChannelInfo.Item.Snippet(
                    country = it?.snippet?.country,
                    customUrl = it?.snippet?.customUrl,
                    description = it?.snippet?.description,
                    localized = ChannelInfo.Item.Snippet.Localized(
                        title = it?.snippet?.localized?.title,
                        description = it?.snippet?.localized?.description
                    ),
                    publishedAt = it?.snippet?.publishedAt,
                    thumbnails = ChannelInfo.Item.Snippet.Thumbnails(
                        default = ChannelInfo.Item.Snippet.Thumbnails.Default(
                            height = it?.snippet?.thumbnails?.default?.height,
                            width = it?.snippet?.thumbnails?.default?.width,
                            url = it?.snippet?.thumbnails?.default?.url
                        ),
                        high =  ChannelInfo.Item.Snippet.Thumbnails.High(
                            height = it?.snippet?.thumbnails?.high?.height,
                            width = it?.snippet?.thumbnails?.high?.width,
                            url = it?.snippet?.thumbnails?.high?.url
                        ),
                        medium =  ChannelInfo.Item.Snippet.Thumbnails.Medium(
                            height = it?.snippet?.thumbnails?.medium?.height,
                            width = it?.snippet?.thumbnails?.medium?.width,
                            url = it?.snippet?.thumbnails?.medium?.url
                        ),

                        ),
                    title = it?.snippet?.title
                )
            )
        } ,
        kind = this.kind,
    )
}