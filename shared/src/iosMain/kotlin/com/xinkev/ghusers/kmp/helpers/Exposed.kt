package com.xinkev.ghusers.kmp.helpers

import com.kuuurt.paging.multiplatform.helpers.dispatcher
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> collect(flow: Flow<T>, block: (T) -> Unit): Closeable {
    val job = Job()
    flow.onEach { block(it) }.launchIn(CoroutineScope(job + dispatcher()))

    return object : Closeable {
        override fun close() {
            job.cancel()
        }
    }
}

val defaultCoroutineScopeForIOS = CoroutineScope(SupervisorJob() + Dispatchers.Default)
