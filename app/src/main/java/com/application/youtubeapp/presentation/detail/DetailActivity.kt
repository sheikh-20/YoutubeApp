package com.application.youtubeapp.presentation.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.application.youtubeapp.R
import com.application.youtubeapp.base.BaseActivity
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.ActivityDetailBinding
import com.application.youtubeapp.databinding.ActivityMainBinding
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

        detailViewModel.getVideoDetails(intent.getStringExtra(VIDEO_ID) ?:  return)
        detailViewModel.videoDetail.observe(this) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Failure -> {

                }
                is Resource.Success -> {
                    binding.pvVideo.load(it.data.items?.first()?.snippet?.thumbnails?.high?.url)
                    binding.tvDuration.text = it.data.items?.first()?.contentDetails?.duration
                }
            }
        }
    }
}