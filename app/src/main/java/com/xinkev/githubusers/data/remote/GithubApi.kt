package com.xinkev.githubusers.data.remote

import com.xinkev.githubusers.data.remote.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    suspend fun userList(): Response<List<UsersResponse>>
}
