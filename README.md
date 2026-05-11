# EveryLive Server

This repository is now a backend-only Spring Boot project for Java/Spring engineers.
All previous Kotlin Multiplatform, Compose, iOS, Web, and shared-client modules have been removed from the active codebase so the repository only contains backend-related files.

## Project layout

```text
.
├── server/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/kotlin/com/example/everylive/EveryLiveServerApplication.kt
│       ├── main/resources/application.yml
│       └── test/kotlin/com/example/everylive/EveryLiveServerApplicationTest.kt
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
└── gradlew.bat
```

## Requirements

- JDK 17+
- Gradle Wrapper from this repository

## Run

macOS/Linux:

```shell
./gradlew :server:bootRun
```

Windows:

```shell
.\gradlew.bat :server:bootRun
```

The server starts on port `8080` by default.

## Endpoints

- `GET /` returns `EveryLive Spring Boot server is running`
- `GET /health` returns `{ "status": "UP" }`

## Test

macOS/Linux:

```shell
./gradlew :server:test
```

Windows:

```shell
.\gradlew.bat :server:test
```
