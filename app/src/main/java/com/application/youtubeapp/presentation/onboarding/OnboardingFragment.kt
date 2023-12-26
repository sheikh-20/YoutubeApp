package com.application.youtubeapp.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.application.youtubeapp.R
import com.application.youtubeapp.adapter.OnboardWelcomeAdapter
import com.application.youtubeapp.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var adapter: OnboardWelcomeAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OnboardWelcomeAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.view.isEnabled = false
        }.attach()
    }
}