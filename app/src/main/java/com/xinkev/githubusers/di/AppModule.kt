package com.xinkev.githubusers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun json(): Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
    }
}
