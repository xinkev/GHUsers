package com.xinkev.githubusers.userList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xinkev.githubusers.ui.models.UiState
import com.xinkev.githubusers.userList.composables.UserList
import com.xinkev.githubusers.userList.composables.UserListError

@Composable
fun UserListScreen(vm: UserListViewModel) {
    val state = vm.state.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (state) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            is UiState.Error -> UserListError(message = state.message, onClick = vm::getUserList)
            is UiState.Ready -> UserList(items = state.data)
        }
    }
}
