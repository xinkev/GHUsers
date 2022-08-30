package com.xinkev.githubusers.models

data class User(
    val id: Int,
    val username: String,
    val avatar: String,
    val type: String,
    val isAdmin: Boolean
)
