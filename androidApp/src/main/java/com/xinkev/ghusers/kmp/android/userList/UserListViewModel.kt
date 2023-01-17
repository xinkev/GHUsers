package com.xinkev.ghusers.kmp.android.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.xinkev.ghusers.kmp.domain.repositories.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class UserListViewModel @Inject constructor(
    repository: GithubUserRepository
) : ViewModel() {
    val userList = repository.getList(viewModelScope).pagingData
        .cachedIn(viewModelScope)
}
