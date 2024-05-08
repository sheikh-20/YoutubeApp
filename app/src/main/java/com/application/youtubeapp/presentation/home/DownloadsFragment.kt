package com.application.youtubeapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.youtubeapp.R
import com.application.youtubeapp.databinding.FragmentDownloadsBinding

class DownloadsFragment : Fragment() {

    private lateinit var binding: FragmentDownloadsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDownloadsBinding.inflate(inflater, container, false)
        return binding.root
    }

}