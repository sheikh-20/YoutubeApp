package com.application.youtubeapp.presentation.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.application.youtubeapp.databinding.ActivityOnboardingBinding
import com.application.youtubeapp.presentation.home.HomeActivity
import com.application.youtubeapp.presentation.viewmodel.OnboardingViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    var gso: GoogleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
            .requestScopes(Scope("https://www.googleapis.com/auth/youtube.readonly"))
            .requestIdToken("313575591612-mevqe2m6mgk2rcf2nrdcklllct9kctp4.apps.googleusercontent.com")
            .build()

    private val signIn =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    account.idToken?.let { token ->
                        onboardingViewModel.signIn(this, token)
                        HomeActivity.startActivity(this)
                    }
                } catch (e: ApiException) {
                    Timber.tag(TAG).e(e, "Sign-in failed")
                }
            }
        }

    companion object {
        const val TAG = "OnboardActivity"

        fun startActivity(activity: Activity?) {
            val intent = Intent(activity, OnboardingActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        binding.acceptBtn.setOnClickListener { signIn.launch(signInIntent) }
    }
}