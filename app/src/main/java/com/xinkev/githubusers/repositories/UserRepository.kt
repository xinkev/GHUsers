package com.xinkev.githubusers.repositories

import com.xinkev.githubusers.data.remote.GithubApi
import com.xinkev.githubusers.models.Outcome
import com.xinkev.githubusers.models.User
import com.xinkev.githubusers.models.UserList
import com.xinkev.githubusers.utils.CoroutineDispatchers
import com.xinkev.githubusers.utils.handleApi
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val api: GithubApi
) {
    suspend fun getUsers(): Outcome<UserList> {
        return withContext(dispatchers.io) {
            handleApi(map = { res ->
                res.map {
                    User(
                        username = it.login,
                        avatar = it.avatarUrl.orEmpty(),
                        type = it.type,
                        isAdmin = it.siteAdmin
                    )
                }
            }) {
                api.userList()
            }
        }
    }
}
