package com.application.youtubeapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.application.youtubeapp.databinding.FragmentOnboardBinding
import com.application.youtubeapp.presentation.onboarding.OnboardingActivity.Companion.TAG
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
class OnboardFragment: Fragment() {

    private lateinit var binding: FragmentOnboardBinding
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()

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
                        onboardingViewModel.signIn(requireActivity(), token)
                    }
                } catch (e: ApiException) {
                    Timber.tag(TAG).e(e, "Sign-in failed")
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentOnboardBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        binding.acceptBtn.setOnClickListener { signIn.launch(signInIntent) }
    }
}