package com.xinkev.githubusers.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xinkev.githubusers.data.pagination.UserSource
import com.xinkev.githubusers.data.remote.GithubApi
import com.xinkev.githubusers.di.Dispatcher
import com.xinkev.githubusers.di.DispatcherType.IO
import com.xinkev.githubusers.models.Outcome
import com.xinkev.githubusers.models.User
import com.xinkev.githubusers.models.UserDetails
import com.xinkev.githubusers.utils.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userSource: UserSource,
    private val api: GithubApi,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) {
    val userListFlow: Flow<PagingData<User>>
        get() = Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { userSource }
        ).flow

    suspend fun getDetails(username: String): Outcome<UserDetails> {
        return withContext(ioDispatcher) {
            handleApi(map = { response ->
                UserDetails(
                    id = response.id,
                    avatarUrl = response.avatarUrl,
                    username = response.login,
                    name = response.name,
                    followers = response.followers,
                    following = response.following,
                    publicGists = response.publicGists,
                    publicRepos = response.publicRepos,
                    contactInfo = UserDetails.ContactInfo(
                        twitter = response.twitterUsername,
                        github = response.htmlUrl,
                        blog = response.blog,
                        email = response.email
                    ),
                    bio = response.bio
                )
            }) {
                api.getUserDetails(username)
            }
        }
    }
}
