package com.xinkev.githubusers.userDetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xinkev.githubusers.ui.composables.Avatar

@Composable
fun UserDetailsHeader(
    avatarUrl: String,
    name: String?,
    username: String,
    followers: Int,
    following: Int,
    repos: Int,
    gists: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar(
            modifier = Modifier
                .size(114.dp)
                .padding(bottom = 8.dp),
            url = avatarUrl
        )
        if (name != null) {
            Text(text = name, style = MaterialTheme.typography.h5)
        }
        Text(
            text = "@$username",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.typography.h6.color.copy(alpha = 0.6f)
        )
        Row(
            Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Counter(what = "followers", count = followers)
            Counter(what = "following", count = following)
            Counter(what = "repos", count = repos)
            Counter(what = "gists", count = gists)
        }
    }
}

@Composable
private fun Counter(
    what: String,
    count: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = what,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.typography.caption.color.copy(alpha = 0.3f)
        )
        Text(text = count.toString())
    }
}
