package com.xinkev.ghusers.kmp.data.remote

import com.xinkev.ghusers.kmp.data.remote.models.UserDetailsResponse
import com.xinkev.ghusers.kmp.data.remote.models.UserRepoResponse
import com.xinkev.ghusers.kmp.data.remote.models.UserResponse
import com.xinkev.ghusers.kmp.domain.exceptions.ApiException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

internal class GithubAPI(private val client: HttpClient) {

    /**
     * Get a list of users from Github. The list contains both personal and organisation accounts.
     *
     * @param since A user ID. Only return users with an ID greater than this ID.
     * @param perPage The number of results per page (max 100).
     *
     */
    @Throws(ApiException::class, CancellationException::class)
    suspend fun userList(
        since: String?,
        perPage: String? = null
    ): List<UserResponse> {
        val response = client.get("users") {
            url {
                since?.let { parameters.append("since", it) }
                perPage?.let { parameters.append("per_page", it) }
            }
        }
        return handle(response)
    }

    suspend fun getUserDetails(username: String): UserDetailsResponse {
        val response = client.get("users/$username")
        return handle(response)
    }

    /**
     * Get a list of public repo of a user.
     *
     *  [Docs](https://docs.github.com/en/rest/repos/repos#list-repositories-for-a-user)
     */
    suspend fun getUserRepos(
        username: String,
        type: String? = null,
        sort: String? = null,
        direction: String? = null,
        perPage: String? = null,
        page: String? = null
    ): List<UserRepoResponse> {
        val response = client.get("users/$username/repos") {
            url {
                parameters.apply {
                    type?.let { append("type", it) }
                    sort?.let { append("sort", it) }
                    direction?.let { append("direction", it) }
                    perPage?.let { append("per_page", it) }
                    page?.let { append("page", it) }
                }
            }
        }
        return handle(response)
    }

    companion object {
        private val client = HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        encodeDefaults = true
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            defaultRequest {
                url("https://api.github.com")
                header("Accept", "application/vnd.github+json")
            }
        }
        val instance = GithubAPI(client)
    }
}

private suspend inline fun <reified T> handle(response: HttpResponse): T {
    return when (response.status) {
        HttpStatusCode.OK -> response.body()
        HttpStatusCode.Forbidden -> throw ApiException(
            "Whoops! You just exceeded the GitHub API rate limit."
        )
        else -> throw ApiException("Received a ${response.status}.")
    }
}
