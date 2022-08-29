package com.xinkev.githubusers.models

data class UserDetails(
    val id: Int,
    val username: String,
    val name: String,
    val twitter: String?,
    val github: String?,
    val blog: String?,
    val email: String?,
    val followers: Int,
    val following: Int
)
