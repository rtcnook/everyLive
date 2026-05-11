package com.example.everylive

import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val AppRed = Color(0xFFF2484F)
internal val DarkRed = Color(0xFFD93D41)
internal val AppGray = Color(0xFF8C8C8C)
internal val LightGray = Color(0xFFEDEDED)
internal val PageBackground = Color(0xFFFAFAFA)

internal val EveryLiveLightScheme = lightColorScheme(
    primary = AppRed,
    secondary = DarkRed,
    background = PageBackground,
    surface = Color.White,
    surfaceVariant = Color(0xFFF3F3F3),
    onPrimary = Color.White,
    onBackground = Color(0xFF6F6F6F),
    onSurface = Color(0xFF6F6F6F),
)
internal val EveryLiveTypography = Typography()
