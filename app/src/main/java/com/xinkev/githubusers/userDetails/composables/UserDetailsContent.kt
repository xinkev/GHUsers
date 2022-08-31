package com.xinkev.githubusers.userDetails.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.xinkev.githubusers.models.Repo
import com.xinkev.githubusers.models.UserDetails

@Composable
fun UserDetailsContent(
    modifier: Modifier = Modifier,
    details: UserDetails,
    repos: LazyPagingItems<Repo>
) {
    Column(modifier = modifier) {
        UserDetailsHeader(
            avatarUrl = details.avatarUrl,
            name = details.name,
            username = details.username,
            following = details.following,
            followers = details.followers,
            gists = details.publicGists,
            repos = details.publicRepos,
            contactInfo = details.contactInfo
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (details.bio != null) {
            Bio(details.bio)
        }
        UserRepoList(repos = repos)
    }
}

@Composable
private fun Bio(bio: String) {
    Text(text = "Bio", style = MaterialTheme.typography.h6)
    Text(bio)
}
