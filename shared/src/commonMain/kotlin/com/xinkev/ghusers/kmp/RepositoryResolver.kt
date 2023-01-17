package com.xinkev.ghusers.kmp

import com.xinkev.ghusers.kmp.domain.repositories.GithubRepoRepository
import com.xinkev.ghusers.kmp.domain.repositories.GithubUserRepository

interface IRepositoryResolver {
    val githubRepoRepository: GithubRepoRepository
    val githubUserRepository: GithubUserRepository
}

expect object RepositoryResolver : IRepositoryResolver
