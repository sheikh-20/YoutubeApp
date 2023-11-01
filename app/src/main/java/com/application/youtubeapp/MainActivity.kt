package com.application.youtubeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.youtubeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tv.setOnClickListener {
            binding.tv.text = "Hello Developer!"
        }
    }
}