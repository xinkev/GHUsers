@file:Suppress("SameParameterValue")

package com.xinkev.ghusers.kmp.android.userDetails.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xinkev.ghusers.kmp.android.R
import com.xinkev.ghusers.kmp.domain.models.UserDetails

@Composable
fun VCard(contactInfo: UserDetails.ContactInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        with(contactInfo) {
            github?.let {
                VcardButton(
                    drawable = R.drawable.ic_github,
                    description = "Click to go to twitter profile",
                    uri = "https://github.com/$it"
                )
            }
            email?.let {
                VcardButton(
                    imageVector = Filled.Email,
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
                    imageVector = Filled.Link,
                    description = "",
                    uri = it
                )
            }
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
