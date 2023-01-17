package com.xinkev.ghusers.kmp

import com.xinkev.ghusers.kmp.data.repositories.DefaultGithubRepoRepository
import com.xinkev.ghusers.kmp.data.repositories.DefaultGithubUserRepository
import com.xinkev.ghusers.kmp.domain.repositories.GithubRepoRepository
import com.xinkev.ghusers.kmp.domain.repositories.GithubUserRepository
import kotlinx.coroutines.Dispatchers

actual object RepositoryResolver : IRepositoryResolver {
    private val dispatcher = Dispatchers.IO
    override val githubRepoRepository: GithubRepoRepository
        get() = DefaultGithubRepoRepository.create(dispatcher)
    override val githubUserRepository: GithubUserRepository
        get() = DefaultGithubUserRepository.create(dispatcher)
}
