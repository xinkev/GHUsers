package com.xinkev.ghusers.kmp.android.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xinkev.ghusers.kmp.android.utils.composeView
import com.xinkev.ghusers.kmp.android.utils.navController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private val viewModel by viewModels<UserDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        UserDetailsScreen(
            vm = viewModel,
            navigateUp = navController::navigateUp
        )
    }
}
