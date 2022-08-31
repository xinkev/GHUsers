package com.xinkev.githubusers.models

data class Repo(
    val id: Int,
    val fullName: String,
    val archived: Boolean,
    val hasIssues: Boolean,
    val description: String?,
    val forksCount: Int,
    val starGazersCount: Int,
    val openIssuesCount: Int,
    val htmlUrl: String,
    val language: String?,
    val license: String?,
    val updatedAt: String?
)
