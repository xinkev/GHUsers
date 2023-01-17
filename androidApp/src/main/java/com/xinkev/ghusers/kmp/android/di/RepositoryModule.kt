package com.xinkev.ghusers.kmp.android.di

import com.xinkev.ghusers.kmp.RepositoryResolver
import com.xinkev.ghusers.kmp.domain.repositories.GithubRepoRepository
import com.xinkev.ghusers.kmp.domain.repositories.GithubUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun userRepo(): GithubUserRepository = RepositoryResolver.githubUserRepository

    @Provides
    fun repoRepository(): GithubRepoRepository = RepositoryResolver.githubRepoRepository
}
