# EveryLive Server

This repository contains the EveryLive Compose Multiplatform client and a Java Spring Boot backend.
The Compose UI is shared across Android, iOS, Web, and desktop targets, while [`server`](./server) is a plain Java backend module intended for Java/Spring engineers.

## Project layout

```text
.
├── composeApp/              # Shared Compose Multiplatform UI for Android, Web, Desktop, and iOS framework output
├── iosApp/                  # Native iOS app shell that hosts the shared Compose UI
├── server/                  # Java Spring Boot backend application for Java/Spring engineers
│   ├── src/main/java/       # Spring Boot entry point and REST controllers
│   ├── src/main/resources/  # Spring Boot configuration
│   └── src/test/java/       # Spring Boot tests
└── shared/                  # Shared Kotlin code used by the multiplatform client
```

## Backend Technology Stack

The `server` module intentionally uses Java source files and Spring Boot conventions so Java backend engineers do not need to read or maintain Kotlin backend code.

- Java 17
- Spring Boot Web
- JUnit 5 / Spring Boot Test

## Build and Run Server

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

## Test Server

macOS/Linux:

```shell
./gradlew :server:test
```

Windows:

```shell
.\gradlew.bat :server:test
```


## Build and Run Android Application

Use the Android Studio run configuration or build directly:

```shell
./gradlew :composeApp:assembleDebug
```

## Build and Run Desktop Application

```shell
./gradlew :composeApp:run
```

## Build and Run Web Application

```shell
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

## Build and Run iOS Application

Open [`iosApp`](./iosApp) in Xcode and run the native shell app.
