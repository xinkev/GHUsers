package com.xinkev.githubusers.ui.models

sealed class UiState<out T> {
    data class Ready<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}
