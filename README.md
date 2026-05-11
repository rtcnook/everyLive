# EveryLive

This repository contains the EveryLive Compose Multiplatform client and a Java Spring Boot backend.
The project is split into three active Gradle modules: `composeApp` for shared UI, `shared` for cross-platform business/domain logic, and `server` for the plain Java Spring Boot backend used by Java/Spring engineers.

## Project layout

```text
.
├── composeApp/              # Shared Compose Multiplatform UI only
├── shared/                  # Shared Kotlin business/domain logic used by Compose targets
├── iosApp/                  # Native iOS app shell that hosts the shared Compose UI
├── server/                  # Java Spring Boot backend application for Java/Spring engineers
│   ├── pom.xml              # Maven build for the Java Spring Boot backend
│   ├── src/main/java/       # Spring Boot entry point and REST controllers
│   ├── src/main/resources/  # Spring Boot configuration
│   └── src/test/java/       # Spring Boot tests
```


## Module Responsibilities

- `composeApp`: shared Compose Multiplatform UI screens, theme, navigation, and UI components.
- `shared`: platform-independent Kotlin logic and models that can be reused by Android, iOS, Web, and desktop clients.
- `server`: Java Spring Boot backend API used by the client; keep this module Java-friendly for backend engineers.

`composeApp` depends on `shared`, while `server` is an independent Maven backend project that communicates with clients over HTTP APIs.

## Backend Technology Stack

The `server` module intentionally uses Java source files and Spring Boot conventions so Java backend engineers do not need to read or maintain Kotlin backend code.

- Java 17
- Maven 3.9+
- Spring Boot Web
- JUnit 5 / Spring Boot Test

## Build and Run Server

macOS/Linux:

```shell
cd server
mvn spring-boot:run
```

Windows:

```shell
cd server
mvn spring-boot:run
```

The server starts on port `8080` by default.

## Endpoints

- `GET /` returns a plain-text server status message.
- `GET /health` returns a simple health payload.

## Test Server

macOS/Linux:

```shell
cd server
mvn test
```

Windows:

```shell
cd server
mvn test
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
