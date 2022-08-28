package com.xinkev.githubusers.models

sealed class Outcome<out T> {
    data class Success<out T>(val data: T) : Outcome<T>()
    data class Failure(val message: String) : Outcome<Nothing>()
}
