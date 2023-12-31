package com.application.youtubeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.youtubeapp.databinding.VideoCategoryViewBinding
import com.application.youtubeapp.domain.model.VideoCategory

class VideoCategoryAdapter: ListAdapter<VideoCategory, VideoCategoryAdapter.VideoCategoryViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<VideoCategory>() {
        override fun areItemsTheSame(oldItem: VideoCategory, newItem: VideoCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoCategory, newItem: VideoCategory): Boolean {
            return oldItem.snippet?.title == newItem.snippet?.title
        }
    }

    class VideoCategoryViewHolder(private var binding: VideoCategoryViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(videoCategory: VideoCategory) {
            binding.btnTitle.text = videoCategory.snippet?.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoCategoryViewHolder {
        return VideoCategoryViewHolder(VideoCategoryViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VideoCategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}