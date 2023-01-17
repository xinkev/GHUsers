package com.xinkev.ghusers.kmp.data.remote.models

import com.xinkev.ghusers.kmp.domain.models.Repo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserRepoResponse(
    val archived: Boolean,
    val description: String?,
    @SerialName("forks_count")
    val forksCount: Int,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("has_issues")
    val hasIssues: Boolean,
    @SerialName("html_url")
    val htmlUrl: String,
    val id: Int,
    val language: String?,
    val license: License?,
    @SerialName("open_issues_count")
    val openIssuesCount: Int,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("updated_at")
    val updatedAt: String?
) {
    @Serializable
    data class License(
        val name: String
    )
}

internal fun UserRepoResponse.toDomainModel() = Repo(
    id = id,
    fullName = fullName,
    archived = archived,
    hasIssues = hasIssues,
    openIssuesCount = openIssuesCount,
    starGazersCount = stargazersCount,
    forksCount = forksCount,
    description = description,
    htmlUrl = htmlUrl,
    language = language,
    license = license?.name,
    updatedAt = updatedAt
)
