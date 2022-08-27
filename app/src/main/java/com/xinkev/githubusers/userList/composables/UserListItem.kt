package com.xinkev.githubusers.userList.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.xinkev.githubusers.models.User

@Composable
fun UserListItem(modifier: Modifier = Modifier, user: User) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(modifier = Modifier.padding(8.dp), url = user.avatar)
        Username(value = user.username)
    }
}

@Composable
private fun Username(value: String) {
    Text(text = value, style = MaterialTheme.typography.subtitle1)
}

@Composable
private fun Avatar(modifier: Modifier, url: String) {
    SubcomposeAsyncImage(
        modifier = modifier.then(
            Modifier
                .size(40.dp)
                .clip(CircleShape)
        ),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(enable = true)
            .build(),
        contentDescription = "Avatar",
        error = {
            Icon(
                imageVector = Icons.Outlined.ErrorOutline,
                contentDescription = "Avatar failed to load."
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserListItem() {
    UserListItem(
        user = User(
            username = "xinkev",
            avatar = "https://images.unsplash.com/photo-1661459479190-fd1cdad5ffd4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2970&q=80"
        )
    )
}
