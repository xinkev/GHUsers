package com.xinkev.githubusers.userDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xinkev.githubusers.data.repositories.UserRepository
import com.xinkev.githubusers.models.Outcome
import com.xinkev.githubusers.models.UserDetails
import com.xinkev.githubusers.ui.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val userDetails = MutableStateFlow<UiState<UserDetails?>>(UiState.Ready(null))
    val userDetailsState = userDetails.asStateFlow()

    init {
        getUserDetails()
    }

    fun getUserDetails() {
        val username = UserDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).username
        viewModelScope.launch {
            userDetails.update { UiState.Loading }
            when (val outcome = userRepository.getDetails(username)) {
                is Outcome.Success -> userDetails.update { UiState.Ready(outcome.data) }
                is Outcome.Failure -> userDetails.update { UiState.Error(outcome.message) }
            }
        }
    }
}
