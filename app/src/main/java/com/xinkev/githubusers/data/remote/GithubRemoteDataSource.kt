package com.xinkev.githubusers.data.remote

import com.xinkev.githubusers.data.remote.models.UserDetailsResponse
import com.xinkev.githubusers.data.remote.models.UserRepoResponse
import com.xinkev.githubusers.data.remote.models.UserResponse

interface GithubRemoteDataSource {
    /**
     * Get a list of users from Github. The list contains both personal and organisation accounts.
     *
     * @param since A user ID. Only return users with an ID greater than this ID.
     * @param perPage The number of results per page (max 100).
     *
     */
    suspend fun getUserList(
        since: Int? = null,
        perPage: Int? = null
    ): List<UserResponse>

    suspend fun getUserDetails(username: String): UserDetailsResponse

    /**
     * Get a list of public repo of a user.
     *
     *  [Docs](https://docs.github.com/en/rest/repos/repos#list-repositories-for-a-user)
     */
    suspend fun getUserRepos(username: String, page: Int?, perPage: Int?): List<UserRepoResponse>
}
