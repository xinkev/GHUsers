package com.xinkev.githubusers.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xinkev.githubusers.data.remote.GithubRemoteDataSource
import com.xinkev.githubusers.data.remote.models.toDomainModel
import com.xinkev.githubusers.models.Repo
import javax.inject.Inject

class RepoSource @Inject constructor(
    private val remote: GithubRemoteDataSource
) : PagingSource<Int, Repo>() {
    private lateinit var username: String

    fun setUsername(value: String) {
        username = value
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val page = params.key
        val perPage = params.loadSize
        return try {
            val response = remote.getUserRepos(username = username, page = page, perPage = perPage)
            val data = response.map { it.toDomainModel() }
            LoadResult.Page(
                data = data,
                prevKey = if (page == null) null else page - 1,
                nextKey = if (data.isEmpty()) null else (page ?: 1) + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
