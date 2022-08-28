package com.xinkev.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.xinkev.githubusers.ui.theme.GHUsersTheme
import com.xinkev.githubusers.userList.UserListScreen
import com.xinkev.githubusers.userList.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Timber planted!")
        setContent {
            GHUsersTheme {
                UserListScreen(vm = viewModel)
            }
        }
    }
}
