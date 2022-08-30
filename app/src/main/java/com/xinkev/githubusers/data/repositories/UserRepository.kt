package com.xinkev.githubusers.data.repositories

import androidx.paging.PagingData
import com.xinkev.githubusers.models.User
import com.xinkev.githubusers.models.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getList(): Flow<PagingData<User>>
    suspend fun getDetails(username: String): Result<UserDetails>
}
