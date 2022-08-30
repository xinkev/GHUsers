package com.xinkev.githubusers.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xinkev.githubusers.data.remote.GithubRemoteDataSource
import com.xinkev.githubusers.data.remote.models.toDomainModel
import com.xinkev.githubusers.models.User
import javax.inject.Inject

class UserSource @Inject constructor(
    private val remote: GithubRemoteDataSource
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val since = params.key
        val perPage = params.loadSize
        return try {
            val response = remote.getUserList(since = since)
            val data = response.map { it.toDomainModel() }
            LoadResult.Page(
                data = data,
                prevKey = if (since == null) null else since - perPage,
                nextKey = if (data.isEmpty()) null else data.size + data.last().id
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
