package com.example.everylive;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EveryLiveServerApplicationTest {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @LocalServerPort
    private int port;

    @Test
    void rootEndpointReturnsServerStatus() throws Exception {
        String body = get("/");

        assertThat(body).isEqualTo("EveryLive Spring Boot server is running");
    }

    @Test
    void healthEndpointReturnsUp() throws Exception {
        String body = get("/health");

        assertThat(body).contains("\"status\":\"UP\"");
    }

    private String get(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + path))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);
        return response.body();
    }
}
