package com.xinkev.githubusers.userDetails.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xinkev.githubusers.R
import com.xinkev.githubusers.models.Repo
import com.xinkev.githubusers.ui.composables.ErrorView
import com.xinkev.githubusers.ui.composables.IconWithText
import com.xinkev.githubusers.ui.composables.Loading
import com.xinkev.githubusers.ui.models.UiState

@Composable
fun UserRepoList(state: UiState<List<Repo>>, onRetryClick: () -> Unit) {
    Text(text = stringResource(R.string.lbl_repositories), style = MaterialTheme.typography.h6)
    Spacer(modifier = Modifier.height(8.dp))
    when (state) {
        is UiState.Error -> ErrorView(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRetryClick,
            message = state.message
        )
        is UiState.Loading -> Loading(modifier = Modifier.fillMaxWidth())
        is UiState.Ready -> LazyColumn(
            modifier = Modifier.height(400.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.data, key = { it.id }) {
                UserRepo(it)
            }
        }
    }
}

@Composable
private fun UserRepo(repo: Repo) {
    val urlHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .clickable {
                urlHandler.openUri(repo.htmlUrl)
            }
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(15),
                color = MaterialTheme.colors.onBackground
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = repo.fullName,
            style = MaterialTheme.typography.overline,
            color = Color(0xff81bded)
        )
        if (repo.description != null) Text(text = repo.description)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.caption) {
                if (repo.language != null) Text(text = repo.language)
                IconWithText(
                    modifier = Modifier.height(16.dp),
                    text = repo.starGazersCount.toString(),
                    icon = Icons.Outlined.Star
                )
                IconWithText(
                    modifier = Modifier.height(16.dp),
                    text = repo.forksCount.toString(),
                    icon = R.drawable.ic_source_fork
                )
                if (repo.hasIssues) {
                    IconWithText(
                        modifier = Modifier.height(16.dp),
                        text = repo.openIssuesCount.toString(),
                        icon = R.drawable.ic_repo_issues
                    )
                }
            }
        }
    }
}
