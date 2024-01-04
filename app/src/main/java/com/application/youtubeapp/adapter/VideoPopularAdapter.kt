package com.application.youtubeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.application.youtubeapp.databinding.VideoViewBinding
import com.application.youtubeapp.domain.model.PopularVideo
import com.application.youtubeapp.domain.model.VideoCategory
import timber.log.Timber

class VideoPopularAdapter: ListAdapter<PopularVideo.Item, VideoPopularAdapter.VideoPopularViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PopularVideo.Item>() {

        const val TAG = "VideoPopularAdapter"

        override fun areItemsTheSame(oldItem: PopularVideo.Item, newItem: PopularVideo.Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularVideo.Item, newItem: PopularVideo.Item): Boolean {
            return oldItem.snippet?.title == newItem.snippet?.title
        }
    }

    class VideoPopularViewHolder(private var binding: VideoViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(popularVideo: PopularVideo.Item) {
            binding.ivVideoThumbnail.load(popularVideo.snippet?.thumbnails?.standard?.url)
            binding.tvVideoTitle.text = popularVideo.snippet?.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPopularViewHolder {
        return VideoPopularViewHolder(VideoViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VideoPopularViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}