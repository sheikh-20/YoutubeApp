package com.application.youtubeapp.presentation.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.application.youtubeapp.R
import com.application.youtubeapp.base.BaseActivity
import com.application.youtubeapp.databinding.ActivityDetailBinding
import com.application.youtubeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity: BaseActivity() {
    companion object {
        fun startActivity(activity: Activity?) {
            val intent = Intent(activity, DetailActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onResume() {
        super.onResume()
        setTransparentStatusBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}