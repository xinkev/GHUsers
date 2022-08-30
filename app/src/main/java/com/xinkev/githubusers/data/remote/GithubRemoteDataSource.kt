package com.xinkev.githubusers.data.remote

import com.xinkev.githubusers.data.remote.models.UserDetailsResponse
import com.xinkev.githubusers.data.remote.models.UserResponse

interface GithubRemoteDataSource {
    suspend fun getUserList(
        since: Int? = null,
        perPage: Int? = null
    ): List<UserResponse>

    suspend fun getUserDetails(username: String): UserDetailsResponse
}
