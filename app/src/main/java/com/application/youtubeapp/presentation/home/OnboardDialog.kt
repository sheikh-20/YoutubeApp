package com.application.youtubeapp.presentation.home

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.application.youtubeapp.R
import com.application.youtubeapp.databinding.FragmentOnboardBinding

class OnboardDialog : DialogFragment() {

    private lateinit var binding: FragmentOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentOnboardBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        requireActivity().finish()
    }

    override fun getTheme() = R.style.Base_Theme_YoutubeApp_FullScreenDialogTheme
}