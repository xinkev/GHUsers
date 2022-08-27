package com.xinkev.githubusers.data.remote

import com.xinkev.githubusers.data.remote.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubApi {
    @GET("users")
    @Headers("Accept: application/vnd.github+json")
    suspend fun userList(): Response<List<UsersResponse>>
}
