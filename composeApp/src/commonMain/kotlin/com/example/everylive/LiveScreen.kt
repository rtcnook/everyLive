package com.example.everylive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun LiveScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground),
    ) {
        TitleTopBar(title = "直播")
        EmptyLiveState()
    }
}

@Composable
private fun EmptyLiveState() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LiveFailArt(modifier = Modifier.size(170.dp))
            Text("加载失败...", color = Color(0xFF777777), fontSize = 26.sp)
        }
    }
}
