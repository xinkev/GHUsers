package com.xinkev.githubusers.userDetails.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserDetailsToolbar(
    onUpButtonClick: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        IconButton(onClick = onUpButtonClick, modifier = Modifier.padding(4.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Click to go up"
            )
        }
    }
}
