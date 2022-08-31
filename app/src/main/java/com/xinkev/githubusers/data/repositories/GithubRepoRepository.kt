package com.xinkev.githubusers.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xinkev.githubusers.data.pagination.RepoSource
import com.xinkev.githubusers.models.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepoRepository @Inject constructor(
    private val repoSource: RepoSource
) : RepoRepository {
    override fun getUserRepo(username: String): Flow<PagingData<Repo>> {
        repoSource.setUsername(username)
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { repoSource }
        ).flow
    }
}
