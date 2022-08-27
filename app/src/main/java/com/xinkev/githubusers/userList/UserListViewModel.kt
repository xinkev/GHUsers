package com.xinkev.githubusers.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xinkev.githubusers.models.Outcome
import com.xinkev.githubusers.models.UserList
import com.xinkev.githubusers.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val users = MutableStateFlow<UserList>(emptyList())
    val userList = users.asStateFlow()

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            when (val outcome = repository.getUsers()) {
                is Outcome.Success -> {
                    users.update { outcome.data }
                }
                is Outcome.Failure -> {
                    Timber.e(outcome.message)
                }
            }
        }
    }
}
