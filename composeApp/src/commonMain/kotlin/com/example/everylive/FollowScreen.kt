package com.example.everylive

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun FollowScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground),
    ) {
        TitleTopBar(title = "关注")
        LoginRequiredState()
    }
}

@Composable
private fun LoginRequiredState() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-46).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserLoginArt(modifier = Modifier.size(154.dp))
            Spacer(Modifier.height(30.dp))
            Text("当前未登录", color = Color(0xFFA4A4A4), fontSize = 26.sp)
            Spacer(Modifier.height(34.dp))
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .height(52.dp)
                    .width(300.dp),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color(0xFFC95B62)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFC95B62)),
            ) {
                Text("点击登录，精彩不再错过", fontSize = 24.sp)
            }
        }
    }
}
