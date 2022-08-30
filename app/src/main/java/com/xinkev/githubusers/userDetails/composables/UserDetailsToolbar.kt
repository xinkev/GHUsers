package com.xinkev.githubusers.userDetails.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserDetailsToolbar(
    onUpButtonClick: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        IconButton(onClick = onUpButtonClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Click to go up"
            )
        }
    }
}
