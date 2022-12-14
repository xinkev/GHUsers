package com.xinkev.githubusers.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.xinkev.githubusers.data.remote.CacheControlInterceptor
import com.xinkev.githubusers.data.remote.GithubApi
import com.xinkev.githubusers.data.remote.GithubRemoteDataSource
import com.xinkev.githubusers.data.remote.GithubRemoteDataSourceDefault
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface NetworkModule {
    @Binds
    fun remoteDataSource(remote: GithubRemoteDataSourceDefault): GithubRemoteDataSource

    companion object {
        @Provides
        fun chucker(@ApplicationContext context: Context): ChuckerInterceptor =
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()

        @Provides
        fun okhttpClient(
            @ApplicationContext context: Context,
            chucker: ChuckerInterceptor,
            cacheInterceptor: CacheControlInterceptor
        ): OkHttpClient {
            val cacheSize = 5 * 1024 * 1024L // 5MB
            val cache = Cache(context.cacheDir, cacheSize)
            return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .addInterceptor(chucker)
                .build()
        }

        @ExperimentalSerializationApi
        @Provides
        fun retrofit(json: Json, client: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.github.com")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        @Singleton
        @Provides
        fun githubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)
    }
}
