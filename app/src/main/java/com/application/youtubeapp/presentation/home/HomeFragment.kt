package com.application.youtubeapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.application.youtubeapp.R
import com.application.youtubeapp.adapter.VideoCategoryAdapter
import com.application.youtubeapp.adapter.VideoPopularAdapter
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.FragmentHomeBinding
import com.application.youtubeapp.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var adapter: VideoCategoryAdapter
    private lateinit var videoPopularAdapter: VideoPopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getVideoCategory()

        adapter = VideoCategoryAdapter()
        binding.rvVideoCategory.adapter = adapter

        videoPopularAdapter = VideoPopularAdapter()
        binding.rvVideoPopular.adapter = videoPopularAdapter

        homeViewModel.videoCategory.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Failure -> {
                    binding.progressBar.hide()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(it.data)
                }
            }
        }

        lifecycle.coroutineScope.launch {
            homeViewModel.getPopularVideo().collect {
                videoPopularAdapter.submitData(it)
            }
        }
    }
}