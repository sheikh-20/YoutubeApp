package com.application.youtubeapp.presentation.onboarding.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.youtubeapp.R
import com.application.youtubeapp.databinding.FragmentLoginWithPasswordBinding
import com.application.youtubeapp.presentation.home.HomeActivity

class LoginWithPasswordFragment : Fragment() {

    private lateinit var binding: FragmentLoginWithPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginWithPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignin.setOnClickListener {
            requireActivity().finish()
            HomeActivity.startActivity(requireActivity())
        }

        binding.tvSignup.setOnClickListener {
            findNavController().navigate(LoginWithPasswordFragmentDirections.actionLoginWithPasswordFragmentToSignupWithPasswordFragment())
        }

        binding.tvResetPassword.setOnClickListener {
            findNavController().navigate(LoginWithPasswordFragmentDirections.actionLoginWithPasswordFragmentToResetPasswordFragment())
        }
    }
}