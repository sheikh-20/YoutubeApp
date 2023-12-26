package com.application.youtubeapp.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.youtubeapp.R
import com.application.youtubeapp.databinding.FragmentOnboardWelcomeBinding
import com.application.youtubeapp.databinding.FragmentOnboardingBinding


const val ARG_OBJECT = "object"
class OnboardWelcomeFragment : Fragment() {

    private lateinit var binding: FragmentOnboardWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnboardWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.welcomeTv.text = getString(ARG_OBJECT)
        }
    }
}