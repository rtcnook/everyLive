plugins {
    // Keep shared plugin declarations at the root so Gradle loads each plugin
    // once for the multi-module build. Without these apply-false aliases,
    // Kotlin/JS can register Node.js root services twice when both composeApp
    // and shared declare JS/Wasm targets.
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}
