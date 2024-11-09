package com.application.youtubeapp.data.repository

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.youtubeapp.common.Resource
import com.application.youtubeapp.presentation.home.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class AuthRepo @Inject constructor(
    private val context: Context, private val auth: FirebaseAuth
) {

    private companion object {
        const val TAG = "AuthRepo"
    }

    private var _currentUser = MutableLiveData<FirebaseAuth?>(auth)
    val currentUser: LiveData<FirebaseAuth?> get() = _currentUser

    suspend fun signIn(activity: Activity, token: String?): AuthResult {
        val credential = GoogleAuthProvider.getCredential(token, null)
        return auth.signInWithCredential(credential).await()
    }

    fun logout() {
        auth.signOut()
    }
}