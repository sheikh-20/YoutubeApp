package com.application.youtubeapp.presentation.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.target.Target
import coil.transform.CircleCropTransformation
import com.application.youtubeapp.R
import com.application.youtubeapp.adapter.OnItemClickListener
import com.application.youtubeapp.adapter.VideoCategoryAdapter
import com.application.youtubeapp.adapter.VideoPopularAdapter
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.databinding.FragmentHomeBinding
import com.application.youtubeapp.presentation.onboarding.OnboardingActivity
import com.application.youtubeapp.presentation.viewmodel.HomeViewModel
import com.application.youtubeapp.presentation.viewmodel.OnboardingViewModel
import com.application.youtubeapp.utility.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        const val TAG = "HomeFragment"
    }

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val onboardingViewModel: OnboardingViewModel by viewModels()
    private lateinit var adapter: VideoCategoryAdapter

    @Inject
    lateinit var videoPopularAdapter: VideoPopularAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentHomeBinding.inflate(inflater, container, false).also {
            binding = it
            binding.viewmodel = homeViewModel
            binding.lifecycleOwner = viewLifecycleOwner
        }.root

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
        binding.rvVideoCategory.addItemDecoration(MarginItemDecoration(8))

        binding.rvVideoPopular.adapter = videoPopularAdapter
        homeViewModel.videoCategoryResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    adapter.submitList(it.data)
                    homeViewModel.categoryClick(it.data[0].id ?: return@observe)
                }
            }
        }

        onboardingViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it?.currentUser != null) {
                val imageLoader = ImageLoader(requireContext())
                val request = ImageRequest.Builder(requireContext())
                    .data(it.currentUser?.photoUrl)
                    .transformations(CircleCropTransformation())
                    .target(object : Target{
                        override fun onSuccess(result: Drawable) {
                            binding.toolbar.menu.findItem(R.id.account_settings).icon = result
                        }
                        override fun onError(error: Drawable?) {
                            binding.toolbar.menu.findItem(R.id.account_settings).icon = error
                        }
                    })
                    .build()
                imageLoader.enqueue(request)
            } else {
                OnboardingActivity.startActivity(requireActivity())
            }
        }
        homeViewModel.selectedCategory.observe(viewLifecycleOwner) { category ->
            lifecycle.coroutineScope.launch {
                homeViewModel.getPopularVideo(videoCategoryId = category ?: "").collect { video ->
                    videoPopularAdapter.submitData(video)
                }
            }
        }
    }
}