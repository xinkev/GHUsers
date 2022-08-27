package com.xinkev.githubusers.userList

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xinkev.githubusers.models.UserList
import com.xinkev.githubusers.userList.composables.UserListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserListScreen(dataState: State<UserList>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (dataState.value.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(items = dataState.value) {
                    UserListItem(
                        modifier = Modifier.animateItemPlacement(animationSpec = tween(250)),
                        user = it
                    )
                }
            }
        }
    }
}
