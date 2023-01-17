package com.xinkev.ghusers.kmp.android.utils // ktlint-disable filename

fun Throwable.getMessage(): String = localizedMessage ?: message ?: "Something went wrong!"
