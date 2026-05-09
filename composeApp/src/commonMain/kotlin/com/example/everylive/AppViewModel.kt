package com.example.everylive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            runStartupTasks()
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    private suspend fun runStartupTasks() {
        delay(600)
    }
}

data class AppUiState(
    val isLoading: Boolean = true,
)
