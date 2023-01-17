package com.xinkev.ghusers.kmp.android.userDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.xinkev.ghusers.kmp.android.ui.models.UiState
import com.xinkev.ghusers.kmp.android.utils.getMessage
import com.xinkev.ghusers.kmp.domain.models.UserDetails
import com.xinkev.ghusers.kmp.domain.repositories.GithubRepoRepository
import com.xinkev.ghusers.kmp.domain.repositories.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userRepository: GithubUserRepository,
    repoRepository: GithubRepoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val username: String = UserDetailsFragmentArgs
        .fromSavedStateHandle(savedStateHandle).username

    private val userDetails = MutableStateFlow<UiState<UserDetails?>>(UiState.Ready(null))
    val userDetailsState = userDetails.asStateFlow()

    val repoPagedList = repoRepository.getUserRepo(username = username, viewModelScope)
        .pagingData
        .cachedIn(viewModelScope)

    init {
        getUserDetails()
    }

    fun getUserDetails() {
        viewModelScope.launch {
            userDetails.update { UiState.Loading }
            runCatching {
                userRepository.getDetails(username)
            }.fold(
                onSuccess = { data -> userDetails.update { UiState.Ready(data) } },
                onFailure = { t -> userDetails.update { UiState.Error(t.getMessage()) } }
            )
        }
    }
}
