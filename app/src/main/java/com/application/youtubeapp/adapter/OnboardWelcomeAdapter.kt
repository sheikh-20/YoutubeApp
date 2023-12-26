package com.application.youtubeapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.application.youtubeapp.presentation.onboarding.ARG_OBJECT
import com.application.youtubeapp.presentation.onboarding.OnboardWelcomeFragment

class OnboardWelcomeAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = OnboardWelcomeFragment()

        when (position) {
            0 -> {
                fragment.arguments = Bundle().apply {
                    putString(ARG_OBJECT, "The best streaming app of the century to entertain you everyday")
                }
            }
            1 -> {
                fragment.arguments = Bundle().apply {
                    putString(ARG_OBJECT, "Watch interesting videos from your smartphone")
                }
            }
            2 -> {
                fragment.arguments = Bundle().apply {
                    putString(ARG_OBJECT, "Let's explore around the world with Youtube now!")
                }
            }
        }

        return fragment
    }

}