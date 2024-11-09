package com.application.youtubeapp.presentation.home

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.ImageLoader
import coil.request.ImageRequest
import coil.target.Target
import coil.transform.CircleCropTransformation
import com.application.youtubeapp.R
import com.application.youtubeapp.base.BaseActivity
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.ActivityMainBinding
import com.application.youtubeapp.presentation.viewmodel.OnboardingViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    companion object {
        const val TAG = "HomeActivity"

        fun startActivity(activity: Activity?) {
            val intent = Intent(activity, HomeActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }
    }

    private lateinit var binding: ActivityMainBinding
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun observerViewModel() {
        onboardingViewModel.currentUser.value?.also { user ->
            val fragment = if (user.currentUser != null) {
                HomeFragment()
            } else {
                OnboardFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(binding.navHostFragment.id, fragment)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        setTransparentStatusBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { onboardingViewModel.loading.value ?: false }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observerViewModel()
    }
}