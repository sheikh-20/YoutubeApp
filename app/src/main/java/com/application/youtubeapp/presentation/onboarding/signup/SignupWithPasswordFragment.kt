package com.application.youtubeapp.presentation.onboarding.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.youtubeapp.R
import com.application.youtubeapp.databinding.FragmentSignupWithPasswordBinding

class SignupWithPasswordFragment : Fragment() {

    private lateinit var binding: FragmentSignupWithPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupWithPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(SignupWithPasswordFragmentDirections.actionSignupWithPasswordFragmentToLoginWithPasswordFragment())
        }
    }
}