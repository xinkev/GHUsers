package com.xinkev.ghusers.kmp.data.repositories

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import com.xinkev.ghusers.kmp.data.remote.GithubAPI
import com.xinkev.ghusers.kmp.data.remote.models.toDomainModel
import com.xinkev.ghusers.kmp.domain.models.User
import com.xinkev.ghusers.kmp.domain.models.UserDetails
import com.xinkev.ghusers.kmp.domain.repositories.GithubUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
internal class DefaultGithubUserRepository constructor(
    private val api: GithubAPI,
    private val dispatcher: CoroutineDispatcher
) : GithubUserRepository {
    private val pagerScope = CoroutineScope(SupervisorJob() + dispatcher)

    override fun getList(coroutineScope: CoroutineScope): Pager<Int, User> {
        val pager = Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            clientScope = pagerScope,
            initialKey = 1,
            getItems = { currentKey, size ->
                val response =
                    api.userList(since = currentKey.toString(), perPage = size.toString())
                val items = response.map { it.toDomainModel() }
                PagingResult(
                    items = items,
                    currentKey = currentKey,
                    prevKey = { if (currentKey == 1) null else currentKey - size },
                    nextKey = { if (items.isEmpty()) null else items.size + items.last().id }
                )
            }
        )
        return pager
    }

    override suspend fun getDetails(username: String): UserDetails {
        return withContext(dispatcher) {
            val response = api.getUserDetails(username)
            response.toDomainModel()
        }
    }

    companion object {
        fun create(
            dispatcher: CoroutineDispatcher = Dispatchers.Default
        ): DefaultGithubUserRepository {
            val api = GithubAPI.instance
            return DefaultGithubUserRepository(
                api = api,
                dispatcher = dispatcher
            )
        }
    }
}
