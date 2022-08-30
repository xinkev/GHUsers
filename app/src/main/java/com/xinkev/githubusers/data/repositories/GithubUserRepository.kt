package com.xinkev.githubusers.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xinkev.githubusers.data.pagination.UserSource
import com.xinkev.githubusers.data.remote.GithubRemoteDataSource
import com.xinkev.githubusers.data.remote.models.toDomainModel
import com.xinkev.githubusers.di.Dispatcher
import com.xinkev.githubusers.di.DispatcherType.IO
import com.xinkev.githubusers.models.User
import com.xinkev.githubusers.models.UserDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserRepository @Inject constructor(
    private val userSource: UserSource,
    private val remote: GithubRemoteDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : UserRepository {
    private val userListFlow: Flow<PagingData<User>>
        get() = Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { userSource }
        ).flow

    override fun getList(): Flow<PagingData<User>> = userListFlow

    override suspend fun getDetails(username: String): Result<UserDetails> {
        return withContext(ioDispatcher) {
            runCatching {
                val response = remote.getUserDetails(username)
                response.toDomainModel()
            }
        }
    }
}
