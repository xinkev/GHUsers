package com.xinkev.githubusers.di

import com.xinkev.githubusers.data.repositories.GithubRepoRepository
import com.xinkev.githubusers.data.repositories.GithubUserRepository
import com.xinkev.githubusers.data.repositories.RepoRepository
import com.xinkev.githubusers.data.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun userRepository(githubUserRepo: GithubUserRepository): UserRepository

    @Binds
    fun repoRepository(githuRepo: GithubRepoRepository): RepoRepository
}
