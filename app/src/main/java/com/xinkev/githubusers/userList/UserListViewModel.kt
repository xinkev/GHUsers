package com.xinkev.githubusers.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.xinkev.githubusers.data.repositories.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    repository: GithubUserRepository
) : ViewModel() {
    val userList = repository.getList().cachedIn(viewModelScope)
}
