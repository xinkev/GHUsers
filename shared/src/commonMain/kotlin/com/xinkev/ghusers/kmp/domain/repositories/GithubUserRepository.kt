package com.xinkev.ghusers.kmp.domain.repositories

import com.kuuurt.paging.multiplatform.Pager
import com.xinkev.ghusers.kmp.domain.exceptions.ApiException
import com.xinkev.ghusers.kmp.domain.models.User
import com.xinkev.ghusers.kmp.domain.models.UserDetails
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.cancellation.CancellationException

interface GithubUserRepository {
    fun getList(
        coroutineScope: CoroutineScope
    ): Pager<Int, User>

    /**
     * Get detail data of a user given a username.
     */
    @Throws(ApiException::class, CancellationException::class)
    suspend fun getDetails(username: String): UserDetails
}
