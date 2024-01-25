package com.application.youtubeapp.presentation.accountsetup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.application.youtubeapp.base.BaseActivity
import com.application.youtubeapp.databinding.ActivityAccountSetupBinding

class AccountSetupActivity : BaseActivity() {

    private lateinit var binding: ActivityAccountSetupBinding

    companion object {
        fun startActivity(activity: Activity?) {
            val intent = Intent(activity, AccountSetupActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun observerViewModel() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()

        binding = ActivityAccountSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}