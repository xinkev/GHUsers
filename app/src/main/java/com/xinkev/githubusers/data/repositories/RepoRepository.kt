package com.xinkev.githubusers.data.repositories

import com.xinkev.githubusers.models.Repo

interface RepoRepository {
    suspend fun getUserRepo(username: String): Result<List<Repo>>
}
