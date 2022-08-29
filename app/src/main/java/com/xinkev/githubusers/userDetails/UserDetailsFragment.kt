package com.xinkev.githubusers.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.xinkev.githubusers.utils.composeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private val navArgs by navArgs<UserDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        Text("id: ${navArgs.id}")
    }
}
