package com.xinkev.githubusers.ui.composables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun Avatar(modifier: Modifier, url: String) {
    SubcomposeAsyncImage(
        modifier = modifier.then(Modifier.clip(CircleShape)),
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
