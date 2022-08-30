package com.xinkev.githubusers.utils // ktlint-disable filename

fun Throwable.getMessage(): String = localizedMessage ?: message ?: "Something went wrong!"
