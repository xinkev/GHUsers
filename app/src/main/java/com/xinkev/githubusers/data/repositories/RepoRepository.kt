package com.xinkev.githubusers.data.repositories

import androidx.paging.PagingData
import com.xinkev.githubusers.models.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getUserRepo(username: String): Flow<PagingData<Repo>>
}
