package com.xinkev.githubusers.data.remote

import com.xinkev.githubusers.data.remote.models.UserDetailsResponse
import com.xinkev.githubusers.data.remote.models.UserRepoResponse
import com.xinkev.githubusers.data.remote.models.UserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRemoteDataSourceDefault @Inject constructor(
    private val client: HttpClient
) : GithubRemoteDataSource {
    override suspend fun getUserList(
        since: Int?,
        perPage: Int?
    ): List<UserResponse> = client.get("users") {
        url {
            parameters.append("since", since.toString())
            parameters.append("per_page", perPage.toString())
        }
    }.body()

    override suspend fun getUserDetails(username: String): UserDetailsResponse =
        client.get("users") {
            url {
                appendPathSegments(username)
            }
        }.body()

    override suspend fun getUserRepos(
        username: String,
        page: Int?,
        perPage: Int?
    ): List<UserRepoResponse> = client.get("users") {
        url {
            appendPathSegments(username, "repos")
            parameters.apply {
                page?.let { append("page", it.toString()) }
                perPage?.let { append("per_page", it.toString()) }
            }
        }
    }.body()
}
