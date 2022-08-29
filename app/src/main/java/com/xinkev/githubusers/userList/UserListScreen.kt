package com.xinkev.githubusers.userList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.xinkev.githubusers.models.User
import com.xinkev.githubusers.ui.composables.ErrorView
import com.xinkev.githubusers.ui.composables.Loading
import com.xinkev.githubusers.userList.composables.UserList

@Composable
fun UserListScreen(
    vm: UserListViewModel,
    navigateToDetails: (User) -> Unit
) {
    val userList = vm.userList.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (val loadState = userList.loadState.refresh) {
            is LoadState.Loading -> Loading(modifier = Modifier.fillMaxSize())
            is LoadState.Error -> {
                ErrorView(
                    modifier = Modifier.fillMaxSize(),
                    throwable = loadState.error,
                    onClick = userList::retry
                )
            }
            is LoadState.NotLoading -> UserList(items = userList, onEntryClick = navigateToDetails)
        }
    }
}
