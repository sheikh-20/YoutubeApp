package com.application.youtubeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.youtubeapp.databinding.VideoCategoryViewBinding
import com.application.youtubeapp.domain.model.VideoCategory
import timber.log.Timber

class VideoCategoryAdapter(private val listener: OnItemClickListener): ListAdapter<VideoCategory, VideoCategoryAdapter.VideoCategoryViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<VideoCategory>() {

        override fun areItemsTheSame(oldItem: VideoCategory, newItem: VideoCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoCategory, newItem: VideoCategory): Boolean {
            return oldItem.snippet?.title == newItem.snippet?.title
        }

        private const val TAG = "VideoCategoryAdapter"
        private val selectedPositions = mutableListOf<Int>()
    }

    class VideoCategoryViewHolder(private var binding: VideoCategoryViewBinding, private val listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

        fun bind(videoCategory: VideoCategory, position: Int) {
            binding.btnTitle.text = videoCategory.snippet?.title
            binding.btnTitle.isChecked = selectedPositions.contains(position)
            binding.btnTitle.setOnClickListener {
                listener.onClick(videoCategory.id ?: return@setOnClickListener, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoCategoryViewHolder {
        return VideoCategoryViewHolder(VideoCategoryViewBinding.inflate(LayoutInflater.from(parent.context)), listener)
    }

    override fun onBindViewHolder(holder: VideoCategoryViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    fun toggleSelection(position: Int) {
        selectedPositions.clear()
        selectedPositions.add(position)
        notifyDataSetChanged()
        Timber.tag(TAG).d(selectedPositions.toString())
    }
}

interface OnItemClickListener {
    fun onClick(categoryId: String, position: Int)
}