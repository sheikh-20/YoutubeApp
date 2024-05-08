package com.application.youtubeapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.VideoViewBinding
import com.application.youtubeapp.domain.model.PopularVideo
import com.application.youtubeapp.domain.model.VideoCategory
import com.application.youtubeapp.domain.usecase.ChannelInfoUseCase
import com.application.youtubeapp.presentation.detail.DetailActivity
import dagger.hilt.android.internal.managers.ViewComponentManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class VideoPopularAdapter @Inject constructor(private val channelInfoUseCase: ChannelInfoUseCase, private val coroutineScope: CoroutineScope): PagingDataAdapter<PopularVideo.Item, VideoPopularAdapter.VideoPopularViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PopularVideo.Item>() {

        const val TAG = "VideoPopularAdapter"

        override fun areItemsTheSame(oldItem: PopularVideo.Item, newItem: PopularVideo.Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularVideo.Item, newItem: PopularVideo.Item): Boolean {
            return oldItem.snippet?.title == newItem.snippet?.title
        }
    }

    inner class VideoPopularViewHolder(private var binding: VideoViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(popularVideo: PopularVideo.Item) {
            binding.ivVideoThumbnail.load(popularVideo.snippet?.thumbnails?.high?.url) {
                scale(Scale.FILL)
                precision(Precision.EXACT)
            }
            binding.tvVideoTitle.text = popularVideo.snippet?.title
            binding.root.setOnClickListener {
                val context = if (itemView.context is ViewComponentManager.FragmentContextWrapper)
                    (itemView.context as ViewComponentManager.FragmentContextWrapper).baseContext
                else
                    itemView.context

                DetailActivity.startActivity(context as Activity, popularVideo.id)
            }

          coroutineScope.launch {
                val channelThumbnail = channelInfoUseCase(channelId = popularVideo.snippet?.channelId ?: "")

                when (channelThumbnail) {
                    is Resource.Loading -> {}
                    is Resource.Failure -> {
                        Timber.tag(TAG).e(channelThumbnail.throwable)
                    }
                    is Resource.Success -> {
                        val url = channelThumbnail.data.items?.first()?.snippet?.thumbnails?.default?.url
                        Timber.tag(TAG).d(url)
                        binding.ivChannelPic.load(url)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPopularViewHolder {
        return VideoPopularViewHolder(VideoViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VideoPopularViewHolder, position: Int) {
        holder.bind(getItem(position) ?: PopularVideo.Item(null,null,null,null))
    }
}