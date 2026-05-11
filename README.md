# EveryLive Server

This repository is now configured to use the Spring Boot server in [`server`](./server) as the active Gradle project.
The previous Kotlin Multiplatform/Compose modules are still present in the repository history and working tree, but they are no longer included from `settings.gradle.kts`.

## Project layout

```text
.
├── server/                  # Active Spring Boot application
│   ├── src/main/kotlin/     # Spring Boot entry point and REST controllers
│   ├── src/main/resources/  # Spring Boot configuration
│   └── src/test/kotlin/     # Spring Boot tests
├── composeApp/              # Legacy client code, not included in Gradle build
├── shared/                  # Legacy shared KMP code, not included in Gradle build
└── iosApp/                  # Legacy iOS shell, not included in Gradle build
```

## Run the server

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

- `GET /` returns a plain-text server status message.
- `GET /health` returns a simple health payload.

## Test

macOS/Linux:

```shell
./gradlew :server:test
```

Windows:

```shell
.\gradlew.bat :server:test
```
