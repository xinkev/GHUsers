package com.xinkev.ghusers.kmp.data.repositories

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import com.xinkev.ghusers.kmp.data.remote.GithubAPI
import com.xinkev.ghusers.kmp.data.remote.models.toDomainModel
import com.xinkev.ghusers.kmp.domain.models.Repo
import com.xinkev.ghusers.kmp.domain.repositories.GithubRepoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
internal class DefaultGithubRepoRepository constructor(
    private val api: GithubAPI
) : GithubRepoRepository {
    override fun getUserRepo(username: String, coroutineScope: CoroutineScope): Pager<Int, Repo> {
        val pager = Pager(
            clientScope = coroutineScope,
            initialKey = 1,
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            getItems = { currentKey, size ->
                val response = api.getUserRepos(
                    username = username,
                    page = currentKey.toString(),
                    perPage = size.toString()
                )
                val items = response.map { it.toDomainModel() }
                PagingResult(
                    items = items,
                    currentKey = currentKey,
                    prevKey = { if (currentKey == 1) null else currentKey - 1 },
                    nextKey = { if (items.isEmpty()) null else currentKey + 1 }
                )
            }
        )

        return pager
    }

    companion object {
        fun create(dispatcher: CoroutineDispatcher): DefaultGithubRepoRepository {
            val api = GithubAPI.instance
            return DefaultGithubRepoRepository(api)
        }
    }
}
