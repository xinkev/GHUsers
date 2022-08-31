package com.xinkev.githubusers.data.remote

import com.xinkev.githubusers.data.remote.models.UserDetailsResponse
import com.xinkev.githubusers.data.remote.models.UserRepoResponse
import com.xinkev.githubusers.data.remote.models.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRemoteDataSourceDefault @Inject constructor(
    private val api: GithubApi
) : GithubRemoteDataSource {
    override suspend fun getUserList(
        since: Int?,
        perPage: Int?
    ): List<UserResponse> = api.userList(since = since, perPage = perPage)

    override suspend fun getUserDetails(username: String): UserDetailsResponse =
        api.getUserDetails(username)

    override suspend fun getUserRepos(
        username: String,
        page: Int?,
        perPage: Int?
    ): List<UserRepoResponse> =
        api.getUserRepos(username = username, page = page, perPage = perPage)
}
