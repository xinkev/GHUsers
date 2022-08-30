package com.xinkev.githubusers.userDetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xinkev.githubusers.models.UserDetails
import com.xinkev.githubusers.ui.composables.Avatar

@Composable
fun UserDetailsHeader(
    avatarUrl: String,
    name: String?,
    username: String,
    followers: Int,
    following: Int,
    repos: Int,
    gists: Int,
    contactInfo: UserDetails.ContactInfo
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
            modifier = Modifier.alpha(.6f),
            text = "@$username",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.typography.h6.color
        )
        Row(
            Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Counter(subject = "followers", count = followers)
            Counter(subject = "following", count = following)
            Counter(subject = "repos", count = repos)
            Counter(subject = "gists", count = gists)
        }
        Spacer(modifier = Modifier.height(8.dp))
        VCard(contactInfo = contactInfo)
    }
}

@Composable
private fun Counter(
    subject: String,
    count: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.alpha(.6f),
            text = subject,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.typography.caption.color
        )
        Text(text = count.toString())
    }
}
