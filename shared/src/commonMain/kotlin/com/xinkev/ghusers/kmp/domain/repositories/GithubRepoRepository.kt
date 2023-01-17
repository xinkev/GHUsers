package com.xinkev.ghusers.kmp.domain.repositories

import com.kuuurt.paging.multiplatform.Pager
import com.xinkev.ghusers.kmp.domain.models.Repo
import kotlinx.coroutines.CoroutineScope

interface GithubRepoRepository {
    fun getUserRepo(
        username: String,
        coroutineScope: CoroutineScope
    ): Pager<Int, Repo>
}
