package com.application.youtubeapp.presentation.onboarding.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.youtubeapp.R
import com.application.youtubeapp.databinding.FragmentLoginWithPasswordBinding

class LoginWithPasswordFragment : Fragment() {

    private lateinit var binding: FragmentLoginWithPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginWithPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }



}