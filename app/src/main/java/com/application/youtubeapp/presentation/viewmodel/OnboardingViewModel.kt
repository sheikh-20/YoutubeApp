package com.application.youtubeapp.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.data.repository.AuthRepo
import com.application.youtubeapp.presentation.home.HomeActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val authRepo: AuthRepo) : ViewModel() {

    val currentUser = authRepo.currentUser

    private var _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private var _authResponse = MutableLiveData<Resource<AuthResult>>(Resource.Loading)
    val authResponse: LiveData<Resource<AuthResult>> get() = _authResponse

    fun signIn(activity: Activity, token: String) = viewModelScope.launch {
        try {
           _authResponse.postValue(Resource.Success(authRepo.signIn(activity, token)))
            HomeActivity.startActivity(activity)
        } catch (exception: ApiException) { _authResponse.value = Resource.Failure(exception) }
    }

    fun logout() {
        authRepo.logout()
    }

    init {
        viewModelScope.launch {
            delay(3_000L)
            _loading.value = false
        }
    }
}