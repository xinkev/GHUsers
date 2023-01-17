package com.xinkev.ghusers.kmp.android.di

import com.xinkev.ghusers.kmp.android.di.DispatcherType.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcherType: DispatcherType)

enum class DispatcherType {
    IO
}

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Dispatcher(IO)
    @Provides
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
