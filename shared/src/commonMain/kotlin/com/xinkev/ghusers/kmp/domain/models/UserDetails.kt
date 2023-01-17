package com.xinkev.ghusers.kmp.domain.models

data class UserDetails(
    val id: Int,
    val username: String,
    val avatarUrl: String,
    val bio: String?,
    val name: String?,
    val followers: Int,
    val following: Int,
    val publicGists: Int,
    val publicRepos: Int,
    val contactInfo: ContactInfo
) {
    data class ContactInfo(
        val twitter: String?,
        val github: String?,
        val blog: String?,
        val email: String?
    )
}
