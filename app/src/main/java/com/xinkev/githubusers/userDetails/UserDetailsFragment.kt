package com.xinkev.githubusers.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xinkev.githubusers.utils.composeView
import com.xinkev.githubusers.utils.navController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        UserDetailsScreen(
            navigateUp = navController::navigateUp
        )
    }
}
