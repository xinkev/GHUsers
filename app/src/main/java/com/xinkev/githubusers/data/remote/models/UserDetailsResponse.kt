package com.xinkev.githubusers.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponse(
    @SerialName("avatar_url")
    val avatarUrl: String,
    val bio: String?,
    val blog: String?,
    val company: String?,
    @SerialName("created_at")
    val createdAt: String,
    val email: String?,
    val followers: Int,
    @SerialName("followers_url")
    val followersUrl: String,
    val following: Int,
    @SerialName("following_url")
    val followingUrl: String,
    @SerialName("gists_url")
    val gistsUrl: String,
    @SerialName("gravatar_id")
    val gravatarId: String?,
    val hireable: Boolean?,
    @SerialName("html_url")
    val htmlUrl: String,
    val id: Int,
    val location: String?,
    val login: String,
    val name: String?,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("organizations_url")
    val organizationsUrl: String,
    @SerialName("public_gists")
    val publicGists: Int,
    @SerialName("public_repos")
    val publicRepos: Int,
    @SerialName("repos_url")
    val reposUrl: String,
    @SerialName("site_admin")
    val siteAdmin: Boolean,
    @SerialName("starred_url")
    val starredUrl: String,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    val type: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val url: String
)
