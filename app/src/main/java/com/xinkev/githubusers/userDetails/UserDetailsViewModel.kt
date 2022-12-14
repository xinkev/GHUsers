package com.xinkev.githubusers.userDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xinkev.githubusers.data.repositories.GithubUserRepository
import com.xinkev.githubusers.data.repositories.RepoRepository
import com.xinkev.githubusers.models.UserDetails
import com.xinkev.githubusers.ui.models.UiState
import com.xinkev.githubusers.utils.getMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userRepository: GithubUserRepository,
    private val repoRepository: RepoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val username: String
        get() = UserDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).username

    private val userDetails = MutableStateFlow<UiState<UserDetails?>>(UiState.Ready(null))
    val userDetailsState = userDetails.asStateFlow()

    val repoPagedList = repoRepository.getUserRepo(username = username)

    init {
        getUserDetails()
    }

    fun getUserDetails() {
        viewModelScope.launch {
            userDetails.update { UiState.Loading }
            userRepository.getDetails(username).fold(
                onSuccess = { data -> userDetails.update { UiState.Ready(data) } },
                onFailure = { t -> userDetails.update { UiState.Error(t.getMessage()) } }
            )
        }
    }
}
