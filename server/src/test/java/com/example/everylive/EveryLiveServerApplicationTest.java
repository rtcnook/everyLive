package com.example.everylive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EveryLiveServerApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void rootEndpointReturnsServerStatus() {
        String body = restTemplate.getForObject("http://localhost:" + port + "/", String.class);

        assertThat(body).isEqualTo("EveryLive Spring Boot server is running");
    }

    @Test
    void healthEndpointReturnsUp() {
        @SuppressWarnings("unchecked")
        Map<String, String> body = restTemplate.getForObject("http://localhost:" + port + "/health", Map.class);

        assertThat(body).containsEntry("status", "UP");
    }
}
