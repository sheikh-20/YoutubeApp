package com.application.youtubeapp.presentation.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.application.youtubeapp.base.BaseActivity
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.ActivityDetailBinding
import com.application.youtubeapp.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity: BaseActivity() {
    companion object {
        const val VIDEO_ID = "video_id"

        fun startActivity(activity: Activity?, videoId: String? = null) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(VIDEO_ID, videoId)
            activity?.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        setTransparentStatusBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel.getVideoDetailsWithInfo(intent.getStringExtra(VIDEO_ID) ?:  return)
        detailViewModel.videoDetailWithInfo.observe(this) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Failure -> {

                }
                is Resource.Success -> {

                    val videoItem = it.data.videoDetail.items?.first()
                    val channelItem = it.data.channelInfo.items?.first()

                    binding.pvVideo.load(videoItem?.snippet?.thumbnails?.high?.url)
                    binding.tvDuration.text = videoItem?.contentDetails?.duration
                    binding.tvVideoTitle.text = videoItem?.snippet?.title

                    binding.tvChannelTitle.text = channelItem?.snippet?.title
                    binding.ivChannelPic.load(channelItem?.snippet?.thumbnails?.high?.url)

                }
            }
        }
    }
}