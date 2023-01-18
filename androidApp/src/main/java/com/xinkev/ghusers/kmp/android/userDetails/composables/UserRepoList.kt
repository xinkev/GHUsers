package com.xinkev.ghusers.kmp.android.userDetails.composables

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.xinkev.ghusers.kmp.android.R
import com.xinkev.ghusers.kmp.android.ui.composables.ErrorView
import com.xinkev.ghusers.kmp.android.ui.composables.IconWithText
import com.xinkev.ghusers.kmp.android.ui.composables.Loading
import com.xinkev.ghusers.kmp.domain.models.Repo

@Composable
fun UserRepoList(repos: LazyPagingItems<Repo>) {
    Text(text = stringResource(R.string.lbl_repositories), style = MaterialTheme.typography.h6)
    Spacer(modifier = Modifier.height(8.dp))
    when (val loadState = repos.loadState.refresh) {
        is LoadState.Error -> ErrorView(
            modifier = Modifier.fillMaxWidth(),
            onClick = { repos.retry() },
            throwable = loadState.error
        )
        is LoadState.Loading -> Loading(modifier = Modifier.fillMaxWidth())
        is LoadState.NotLoading -> LazyColumn(
            modifier = Modifier.height(400.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(repos, key = { it.id }) { repo ->
                if (repo != null) UserRepo(repo)
            }
            if (repos.loadState.append == LoadState.Loading) {
                item {
                    Loading(modifier = Modifier.fillMaxWidth())
                }
            } else if (repos.loadState.append is LoadState.Error) {
                item {
                    ErrorView(
                        modifier = Modifier.fillMaxWidth(),
                        throwable = (repos.loadState.append as Error).cause,
                        onClick = repos::retry
                    )
                }
            }
        }
        else -> {}
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
        if (repo.repoDescription != null) Text(text = repo.repoDescription!!)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            // TODO: Refactor
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.caption) {
                if (repo.language != null) Text(text = repo.language!!)
                IconWithText(
                    modifier = Modifier.height(16.dp),
                    text = repo.starGazersCount.toString(),
                    icon = Outlined.Star
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
