package com.xinkev.githubusers.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xinkev.githubusers.models.Outcome
import com.xinkev.githubusers.models.UserList
import com.xinkev.githubusers.repositories.UserRepository
import com.xinkev.githubusers.ui.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val uiState = MutableStateFlow<UiState<UserList>>(UiState.Ready(emptyList()))
    val state = uiState.asStateFlow()

    init {
        getUserList()
    }

    fun getUserList() {
        viewModelScope.launch {
            uiState.emit(UiState.Loading)
            when (val outcome = repository.getUsers()) {
                is Outcome.Success -> {
                    uiState.value = UiState.Ready(outcome.data)
                }
                is Outcome.Failure -> {
                    uiState.value = UiState.Error(outcome.message)
                }
            }
        }
    }
}
