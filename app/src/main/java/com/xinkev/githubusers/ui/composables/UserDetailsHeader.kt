package com.xinkev.githubusers.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserDetailsHeader(
    avatarUrl: String,
    name: String,
    username: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar(
            modifier = Modifier.size(114.dp),
            url = avatarUrl
        )
        Text(text = name, style = MaterialTheme.typography.h5)
        Text(
            text = username,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.typography.h6.color.copy(alpha = 0.6f)
        )
    }
}
