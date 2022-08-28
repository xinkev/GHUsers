package com.xinkev.githubusers.models

typealias UserList = List<User>

data class User(
    val id: Int,
    val username: String,
    val avatar: String,
    val type: String,
    val isAdmin: Boolean
)
