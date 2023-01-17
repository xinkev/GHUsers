package com.xinkev.ghusers.kmp.android.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
//
//    @Provides
//    fun chucker(@ApplicationContext context: Context): ChuckerInterceptor =
//        ChuckerInterceptor.Builder(context)
//            .collector(ChuckerCollector(context))
//            .maxContentLength(250000L)
//            .redactHeaders(emptySet())
//            .alwaysReadResponseBody(false)
//            .build()
//
//    @Provides
//    fun okhttpClient(
//        @ApplicationContext context: Context,
//        chucker: ChuckerInterceptor,
//        cacheInterceptor: CacheControlInterceptor
//    ): OkHttpClient {
//        val cacheSize = 5 * 1024 * 1024L // 5MB
//        val cache = Cache(context.cacheDir, cacheSize)
//        return OkHttpClient.Builder()
//            .cache(cache)
//            .addInterceptor(cacheInterceptor)
//            .addInterceptor(chucker)
//            .build()
//    }
//
//    @ExperimentalSerializationApi
//    @Provides
//    fun retrofit(json: Json, client: OkHttpClient): Retrofit = Retrofit.Builder()
//        .client(client)
//        .baseUrl("https://api.github.com")
//        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//        .build()
}
