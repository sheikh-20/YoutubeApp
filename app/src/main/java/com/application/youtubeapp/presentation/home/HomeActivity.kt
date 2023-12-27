package com.application.youtubeapp.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.application.youtubeapp.R
import com.application.youtubeapp.base.BaseActivity
import com.application.youtubeapp.databinding.ActivityMainBinding
import com.application.youtubeapp.presentation.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        setTransparentStatusBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                onboardingViewModel.loading.value ?: false
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}