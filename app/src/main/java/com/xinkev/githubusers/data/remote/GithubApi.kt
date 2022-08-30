package com.xinkev.githubusers.data.remote

import com.xinkev.githubusers.data.remote.models.UserDetailsResponse
import com.xinkev.githubusers.data.remote.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    /**
     * Get a list of users from Github. The list contains both personal and organisation accounts.
     *
     * @param since A user ID. Only return users with an ID greater than this ID.
     * @param perPage The number of results per page (max 100).
     *
     */
    @GET("users")
    @Headers("Accept: application/vnd.github+json")
    suspend fun userList(
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int?
    ): List<UserResponse>

    @GET("users/{username}")
    @Headers("Accept: application/vnd.github+json")
    suspend fun getUserDetails(@Path("username") username: String): UserDetailsResponse
}
