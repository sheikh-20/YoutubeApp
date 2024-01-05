package com.application.youtubeapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.application.youtubeapp.data.api.YoutubeApi
import com.application.youtubeapp.data.response.PopularVideoDto

class VideoPopularPagingSource(private val api: YoutubeApi): PagingSource<String, PopularVideoDto.Item>() {
    override fun getRefreshKey(state: PagingState<String, PopularVideoDto.Item>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PopularVideoDto.Item> {

        return try {
            val pageToken = params.key ?: "CDIQAA"

            val apiResult = api.getPopularVideos(pageToken = pageToken)
            val videos = if (apiResult.isSuccessful) {
                apiResult.body()
            } else if (apiResult.code() == 400 || apiResult.code() == 401 || apiResult.code() == 403) {
                throw Throwable()
            } else {
                throw Throwable()
            }

            LoadResult.Page(
                data = videos?.items?.map { it ?: PopularVideoDto.Item(null, null, null, null)} ?: listOf(),
                prevKey = if (pageToken.isNotEmpty()) null else videos?.prevPageToken,
                nextKey = if (videos?.items?.isEmpty() == true) null else videos?.nextPageToken,
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }
}