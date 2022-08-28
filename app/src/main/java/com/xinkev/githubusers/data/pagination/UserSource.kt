package com.xinkev.githubusers.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xinkev.githubusers.data.remote.GithubApi
import com.xinkev.githubusers.models.User
import retrofit2.HttpException
import javax.inject.Inject

class UserSource @Inject constructor(
    private val githubApi: GithubApi
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val since = params.key
        val perPage = params.loadSize
        return try {
            val response = githubApi.userList(since = since)
            val data = response.body().orEmpty().map {
                User(
                    id = it.id,
                    username = it.login,
                    avatar = it.avatarUrl.orEmpty(),
                    type = it.type,
                    isAdmin = it.siteAdmin
                )
            }
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = data,
                    prevKey = if (since == null) null else since - perPage,
                    nextKey = if (data.isEmpty()) null else data.size + data.last().id
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
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
