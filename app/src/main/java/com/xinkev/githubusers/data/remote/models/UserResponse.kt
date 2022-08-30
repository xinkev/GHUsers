package com.xinkev.githubusers.data.remote.models

import com.xinkev.githubusers.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("avatar_url")
    val avatarUrl: String?,
    @SerialName("events_url")
    val eventsUrl: String?,
    @SerialName("followers_url")
    val followersUrl: String?,
    @SerialName("following_url")
    val followingUrl: String?,
    @SerialName("gists_url")
    val gistsUrl: String?,
    @SerialName("gravatar_id")
    val gravatarId: String?,
    @SerialName("html_url")
    val htmlUrl: String?,
    val id: Int,
    val login: String,
    @SerialName("node_id")
    val nodeId: String?,
    @SerialName("organizations_url")
    val organizationsUrl: String?,
    @SerialName("received_events_url")
    val receivedEventsUrl: String?,
    @SerialName("repos_url")
    val reposUrl: String?,
    @SerialName("site_admin")
    val siteAdmin: Boolean,
    @SerialName("starred_url")
    val starredUrl: String?,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String?,
    val type: String,
    val url: String?
)

fun UserResponse.toDomainModel() = User(
    id = id,
    username = login,
    avatar = avatarUrl.orEmpty(),
    type = type,
    isAdmin = siteAdmin
)
