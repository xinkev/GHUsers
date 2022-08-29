package com.xinkev.githubusers.userDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val state = vm.userDetailsState.collectAsState().value

    Scaffold(topBar = { UserDetailsToolbar(onUpButtonClick = navigateUp) }) { innerPadding ->
        when (state) {
            is UiState.Error -> ErrorView(
                modifier = Modifier.fillMaxSize(),
                message = state.message,
                onClick = vm::getUserDetails
            )
            is UiState.Loading -> Loading(modifier = Modifier.fillMaxSize())
            is UiState.Ready -> state.data?.let {
                UserDetailsContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(innerPadding),
                    details = it
                )
            }
        }
    }
}
