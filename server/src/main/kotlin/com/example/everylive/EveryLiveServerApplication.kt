package com.example.everylive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class EveryLiveServerApplication

fun main(args: Array<String>) {
    runApplication<EveryLiveServerApplication>(*args)
}

@RestController
class HealthController {
    @GetMapping("/")
    fun index(): String = "EveryLive Spring Boot server is running"

    @GetMapping("/health")
    fun health(): Map<String, String> = mapOf("status" to "UP")
}
