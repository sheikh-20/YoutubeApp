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
import com.application.youtubeapp.adapter.OnItemClickListener
import com.application.youtubeapp.adapter.VideoCategoryAdapter
import com.application.youtubeapp.adapter.VideoPopularAdapter
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.FragmentHomeBinding
import com.application.youtubeapp.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        const val TAG = "HomeFragment"
    }

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var adapter: VideoCategoryAdapter

    @Inject
    lateinit var videoPopularAdapter: VideoPopularAdapter

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

        adapter = VideoCategoryAdapter(object : OnItemClickListener {
            override fun onClick(categoryId: String, position: Int) {
                homeViewModel.categoryClick(categoryId)
                adapter.toggleSelection(position)
            }
        })
        binding.rvVideoCategory.adapter = adapter


        binding.rvVideoPopular.adapter = videoPopularAdapter

        homeViewModel.videoCategory.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Failure -> {
                    binding.failureText.text = it.throwable.localizedMessage + "Hello World!"
                    binding.progressBar.hide()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(it.data)

                    homeViewModel.categoryClick(it.data[0].id ?: return@observe)
                }
            }
        }

        homeViewModel.selectedCategory.observe(viewLifecycleOwner) { category ->

            Timber.tag(TAG).d(category)

            lifecycle.coroutineScope.launch {
                homeViewModel.getPopularVideo(videoCategoryId = category ?: "").collect { video ->
                    videoPopularAdapter.submitData(video)
                }
            }
        }
    }
}