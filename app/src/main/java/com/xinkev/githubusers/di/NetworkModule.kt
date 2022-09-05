package com.xinkev.githubusers.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.xinkev.githubusers.data.remote.CacheControlInterceptor
import com.xinkev.githubusers.data.remote.GithubRemoteDataSource
import com.xinkev.githubusers.data.remote.GithubRemoteDataSourceDefault
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.OkHttpClient
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

        @Singleton
        @Provides
        fun client(json: Json, okhttp: OkHttpClient) = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(json = json)
            }
            install(DefaultRequest)
            defaultRequest {
                url("https://api.github.com")
                headers {
                    append(HttpHeaders.Accept, "application/vnd.github+json")
                }
            }
            engine {
                preconfigured = okhttp
            }
        }
    }
}
