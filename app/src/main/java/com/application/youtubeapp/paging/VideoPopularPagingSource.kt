package com.application.youtubeapp.paging

import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.application.youtubeapp.data.api.YoutubeApi
import com.application.youtubeapp.data.response.PopularVideoDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class VideoPopularPagingSource(private val api: YoutubeApi, private val coroutineScope: CoroutineScope): PagingSource<String, PopularVideoDto.Item>() {

    companion object {
        private const val TAG = "VideoPopularPagingSource"
    }

    override fun getRefreshKey(state: PagingState<String, PopularVideoDto.Item>): String? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PopularVideoDto.Item> {

        return try {
            val pageToken = params.key ?: ""

            Timber.tag("PageToken").d(pageToken)
            val apiResult = api.getPopularVideos(pageToken = pageToken)
            val videos = if (apiResult.isSuccessful) {
                apiResult.body()
            } else if (apiResult.code() == 400 || apiResult.code() == 401 || apiResult.code() == 403) {
                throw Throwable()
            } else {
                throw Throwable()
            }

            val prevKey = if (pageToken.isEmpty()) null else videos?.prevPageToken
            Timber.tag("PrevKey").d(prevKey)

            LoadResult.Page(
                data = videos?.items?.map { it ?: PopularVideoDto.Item(null, null, null, null)} ?: listOf(),
                prevKey = prevKey,
                nextKey = if (videos?.items?.isEmpty() == true) null else videos?.nextPageToken,
            )
        } catch (throwable: Throwable) {
            Timber.tag(TAG).e(throwable)
            LoadResult.Error(throwable)
        }
    }
}