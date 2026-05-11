package com.example.everylive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EveryLiveServerApplicationTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun rootEndpointReturnsServerStatus() {
        val body = restTemplate.getForObject("http://localhost:$port/", String::class.java)

        assertEquals("EveryLive Spring Boot server is running", body)
    }

    @Test
    fun healthEndpointReturnsUp() {
        val body = restTemplate.getForObject("http://localhost:$port/health", Map::class.java)

        assertEquals("UP", body["status"])
    }
}
