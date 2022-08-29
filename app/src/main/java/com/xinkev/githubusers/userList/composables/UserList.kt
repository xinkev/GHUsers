package com.xinkev.githubusers.userList.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.xinkev.githubusers.models.User
import com.xinkev.githubusers.ui.composables.Avatar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<User>,
    onEntryClick: (User) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
    ) {
        items(items = items, key = { it.id }) { user ->
            user?.let {
                UserListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(animationSpec = tween(250)),
                    user = it,
                    onClick = { onEntryClick(it) }
                )
            }
        }
        if (items.loadState.append == LoadState.Loading) {
            item {
                UserListLoading(modifier = Modifier.fillMaxWidth())
            }
        } else if (items.loadState.append is LoadState.Error) {
            item {
                UserListError(
                    modifier = Modifier.fillMaxWidth(),
                    throwable = (items.loadState.append as LoadState.Error).error,
                    onClick = items::retry
                )
            }
        }
    }
}

@Composable
private fun UserListItem(
    modifier: Modifier = Modifier,
    user: User,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp),
            url = user.avatar
        )
        Text(
            text = user.username,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.weight(1f)
        )
        if (user.isAdmin) {
            OutlinedText(color = Color.Red, text = "Admin")
        }
        OutlinedText(
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color.Blue,
            text = user.type
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserListItem() {
    UserListItem(
        user = User(
            username = "xinkev",
            avatar = "https://images.unsplash.com/photo-1661459479190-fd1cdad5ffd4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2970&q=80",
            type = "user",
            isAdmin = false,
            id = 1
        ),
        onClick = {}
    )
}
