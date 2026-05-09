package com.example.everylive

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform