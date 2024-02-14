package com.application.youtubeapp.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract fun observerViewModel()
}