package com.xinkev.githubusers.userDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xinkev.githubusers.ui.composables.Followers
import com.xinkev.githubusers.ui.composables.UserDetailsHeader
import com.xinkev.githubusers.ui.composables.VCard
import com.xinkev.githubusers.userDetails.composables.UserDetailsToolbar

@Composable
fun UserDetailsScreen(
    navigateUp: () -> Unit
) {
    Scaffold(topBar = { UserDetailsToolbar(onUpButtonClick = navigateUp) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            UserDetailsHeader(
                avatarUrl = "",
                name = "Kevin",
                username = "xinkev"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Followers(followers = 18, following = 5)
            Spacer(modifier = Modifier.height(8.dp))
            VCard(email = "xinkev@icloud.com")
            Spacer(modifier = Modifier.height(16.dp))
            Bio("")
        }
    }
}

@Composable
private fun Bio(bio: String) {
    Text(text = "Bio", style = MaterialTheme.typography.h6)
    Text(bio)
}

@Preview(showBackground = true)
@Composable
private fun UserDetailsScreenPreview() {
    UserDetailsScreen(navigateUp = {})
}
