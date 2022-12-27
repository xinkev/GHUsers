package com.xinkev.githubusers.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform