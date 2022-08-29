@file:Suppress("SameParameterValue")

package com.xinkev.githubusers.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xinkev.githubusers.R

@Composable
fun VCard(
    github: String? = null,
    email: String? = null,
    twitter: String? = null,
    blog: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        github?.let {
            VcardButton(
                drawable = R.drawable.ic_github,
                description = "Click to go to twitter profile",
                uri = "https://github.com/$it"
            )
        }
        email?.let {
            VcardButton(
                imageVector = Icons.Filled.Email,
                description = "",
                uri = "mailto:$it"
            )
        }
        twitter?.let {
            VcardButton(
                drawable = R.drawable.ic_twitter,
                description = "Click to go to twitter profile",
                uri = "https://twitter.com/$it"
            )
        }
        blog?.let {
            VcardButton(
                imageVector = Icons.Filled.Link,
                description = "",
                uri = it
            )
        }
    }
}

@Composable
private fun VcardButton(
    uri: String,
    imageVector: ImageVector,
    description: String
) {
    val uriHandler = LocalUriHandler.current
    IconButton(onClick = { uriHandler.openUri(uri) }) {
        Icon(
            imageVector = imageVector,
            contentDescription = description
        )
    }
}

@Composable
private fun VcardButton(
    @DrawableRes drawable: Int,
    description: String,
    uri: String
) {
    val uriHandler = LocalUriHandler.current
    IconButton(onClick = { uriHandler.openUri(uri) }) {
        Icon(
            painter = rememberAsyncImagePainter(model = drawable),
            contentDescription = description
        )
    }
}
