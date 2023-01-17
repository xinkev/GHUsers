package com.xinkev.ghusers.kmp.android.userList

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
class UserListFragment : Fragment() {
    private val vm: UserListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        UserListScreen(vm = vm) { user ->
            navController.navigate(UserListFragmentDirections.toDetails(user.username))
        }
    }
}
