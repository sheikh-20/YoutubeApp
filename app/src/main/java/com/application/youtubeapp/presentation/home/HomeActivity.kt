package com.application.youtubeapp.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
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

    override fun observerViewModel() {
        TODO("Not yet implemented")
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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)



    }
}