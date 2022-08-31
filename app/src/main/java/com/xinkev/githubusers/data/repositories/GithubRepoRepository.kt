package com.xinkev.githubusers.data.repositories

import com.xinkev.githubusers.data.remote.GithubRemoteDataSource
import com.xinkev.githubusers.data.remote.models.toDomainModel
import com.xinkev.githubusers.di.Dispatcher
import com.xinkev.githubusers.di.DispatcherType.IO
import com.xinkev.githubusers.models.Repo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepoRepository @Inject constructor(
    private val remote: GithubRemoteDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : RepoRepository {
    override suspend fun getUserRepo(username: String): Result<List<Repo>> {
        return withContext(ioDispatcher) {
            runCatching {
                val response = remote.getUserRepos(username)
                response.map { it.toDomainModel() }
            }
        }
    }
}
