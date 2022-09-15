package com.xinkev.githubusers.userDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.xinkev.githubusers.ui.composables.ErrorView
import com.xinkev.githubusers.ui.composables.Loading
import com.xinkev.githubusers.ui.models.UiState
import com.xinkev.githubusers.userDetails.composables.UserDetailsContent
import com.xinkev.githubusers.userDetails.composables.UserDetailsToolbar

@Composable
fun UserDetailsScreen(
    vm: UserDetailsViewModel,
    navigateUp: () -> Unit
) {
    val userDetailsState = vm.userDetailsState.collectAsState().value

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = { UserDetailsToolbar(onUpButtonClick = navigateUp) }
    ) { innerPadding ->
        when (userDetailsState) {
            is UiState.Error -> ErrorView(
                modifier = Modifier.fillMaxSize(),
                message = userDetailsState.message,
                onClick = vm::getUserDetails
            )
            is UiState.Loading -> Loading(modifier = Modifier.fillMaxSize())
            is UiState.Ready -> userDetailsState.data?.let {
                UserDetailsContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                    details = it,
                    repos = vm.repoPagedList.collectAsLazyPagingItems()
                )
            }
        }
    }
}
