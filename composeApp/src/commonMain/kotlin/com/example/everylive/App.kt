package com.example.everylive

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@Preview
fun App() {
    val appViewModel = viewModel { AppViewModel() }
    val uiState by appViewModel.uiState.collectAsState()

    MaterialTheme(
        colorScheme = EveryLiveLightScheme,
        typography = EveryLiveTypography,
    ) {
        AnimatedContent(
            targetState = uiState.isLoading,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "startup-content",
        ) { isLoading ->
            if (isLoading) {
                SplashScreen()
            } else {
                EveryLiveMainScreen()
            }
        }
    }
}
