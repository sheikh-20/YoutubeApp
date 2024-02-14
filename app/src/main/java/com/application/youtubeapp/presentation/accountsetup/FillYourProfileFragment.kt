package com.application.youtubeapp.presentation.accountsetup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.youtubeapp.R
import com.application.youtubeapp.base.BaseFragment
import com.application.youtubeapp.databinding.FragmentFillYourProfileBinding

class FillYourProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentFillYourProfileBinding

    override fun observerViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFillYourProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


}