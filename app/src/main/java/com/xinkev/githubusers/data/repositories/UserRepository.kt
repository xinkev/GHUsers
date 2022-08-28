package com.xinkev.githubusers.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xinkev.githubusers.data.pagination.UserSource
import com.xinkev.githubusers.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userSource: UserSource
) {
    val userListFlow: Flow<PagingData<User>>
        get() = Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { userSource }
        ).flow
}
