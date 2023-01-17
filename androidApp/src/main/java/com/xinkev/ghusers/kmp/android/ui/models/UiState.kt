package com.xinkev.ghusers.kmp.android.ui.models

sealed class UiState<out T> {
    data class Ready<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}
