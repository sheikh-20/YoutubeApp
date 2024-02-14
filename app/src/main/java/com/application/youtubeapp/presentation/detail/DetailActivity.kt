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
import com.google.android.material.snackbar.Snackbar
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

    override fun observerViewModel() {
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

                    binding.tvViews.text = videoItem?.statistics?.viewCount
                    binding.tvLike.text = videoItem?.statistics?.likeCount
                    binding.tvComment.text = videoItem?.statistics?.commentCount
                    binding.tvUploadDate.text = videoItem?.snippet?.publishedAt

                    binding.tvVideoTitle.text = videoItem?.snippet?.title
                    binding.tvChannelTitle.text = channelItem?.snippet?.title
                    binding.ivChannelPic.load(channelItem?.snippet?.thumbnails?.high?.url)
                    binding.tvSubscribers.text = channelItem?.statistics?.subscriberCount

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel.getVideoDetailsWithInfo(intent.getStringExtra(VIDEO_ID) ?:  return)
        observerViewModel()

        binding.apply {
            llLike.setOnClickListener {
                Snackbar.make(binding.root, "Login to use like button", Snackbar.LENGTH_SHORT).show()
            }
            ivLike.setOnClickListener {
                Snackbar.make(binding.root, "Login to use like button", Snackbar.LENGTH_SHORT).show()
            }
            tvLike.setOnClickListener {
                Snackbar.make(binding.root, "Login to use like button", Snackbar.LENGTH_SHORT).show()
            }


            llDislike.setOnClickListener {
                Snackbar.make(binding.root, "Login to use dislike button", Snackbar.LENGTH_SHORT).show()
            }
            ivDislike.setOnClickListener {
                Snackbar.make(binding.root, "Login to use dislike button", Snackbar.LENGTH_SHORT).show()
            }
            tvDislike.setOnClickListener {
                Snackbar.make(binding.root, "Login to use dislike button", Snackbar.LENGTH_SHORT).show()
            }


            llShare.setOnClickListener {
                Snackbar.make(binding.root, "Login to use share button", Snackbar.LENGTH_SHORT).show()
            }
            ivShare.setOnClickListener {
                Snackbar.make(binding.root, "Login to use share button", Snackbar.LENGTH_SHORT).show()
            }
            tvShare.setOnClickListener {
                Snackbar.make(binding.root, "Login to use share button", Snackbar.LENGTH_SHORT).show()
            }


            llDownload.setOnClickListener {
                Snackbar.make(binding.root, "Login to use download button", Snackbar.LENGTH_SHORT).show()
            }
            ivDownload.setOnClickListener {
                Snackbar.make(binding.root, "Login to use download button", Snackbar.LENGTH_SHORT).show()
            }
            tvDownload.setOnClickListener {
                Snackbar.make(binding.root, "Login to use download button", Snackbar.LENGTH_SHORT).show()
            }


            llPlaylist.setOnClickListener {
                Snackbar.make(binding.root, "Login to use playlist button", Snackbar.LENGTH_SHORT).show()
            }
            ivPlaylist.setOnClickListener {
                Snackbar.make(binding.root, "Login to use playlist button", Snackbar.LENGTH_SHORT).show()
            }
            tvPlaylist.setOnClickListener {
                Snackbar.make(binding.root, "Login to use playlist button", Snackbar.LENGTH_SHORT).show()
            }

            tvSubscribeBtn.setOnClickListener {
                Snackbar.make(binding.root, "Login to use subscribe button", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}