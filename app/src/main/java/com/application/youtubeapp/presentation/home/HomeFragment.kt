package com.application.youtubeapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.application.youtubeapp.R
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.FragmentHomeBinding
import com.application.youtubeapp.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

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

        homeViewModel.videoCategory.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.text.text = "Loading"
                }
                is Resource.Failure -> {
                    binding.text.text = it.throwable.message
                }
                is Resource.Success -> {
                    binding.text.text = it.data.toString()
                }
            }
        }
    }
}