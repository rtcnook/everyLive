package com.example.everylive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun HomeTopBar() {
    Surface(color = Color.White, shadowElevation = 3.dp) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(98.dp)
                    .padding(horizontal = 22.dp),
            ) {
                SearchIcon(
                    modifier = Modifier
                        .size(42.dp)
                        .align(Alignment.CenterStart),
                    color = AppGray,
                )
                BrandLogo(
                    modifier = Modifier
                        .height(48.dp)
                        .align(Alignment.Center),
                )
                MailIcon(
                    modifier = Modifier
                        .size(38.dp)
                        .align(Alignment.CenterEnd),
                    color = AppGray,
                )
            }
            HorizontalDivider(color = Color(0xFFF0F0F0), thickness = 1.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .width(92.dp)
                        .fillMaxHeight()
                        .background(Color.White),
                    contentAlignment = Alignment.Center,
                ) {
                    MenuIcon(modifier = Modifier.size(34.dp), color = Color(0xFF9E9E9E))
                }
            }
        }
    }
}

@Composable
internal fun TitleTopBar(title: String) {
    Surface(color = Color.White, shadowElevation = 1.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(98.dp)
                .padding(horizontal = 22.dp),
        ) {
            SearchIcon(
                modifier = Modifier
                    .size(42.dp)
                    .align(Alignment.CenterStart),
                color = AppGray,
            )
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xFF6F6F6F),
                fontSize = 34.sp,
                fontWeight = FontWeight.Normal,
            )
            MailIcon(
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.CenterEnd),
                color = AppGray,
            )
        }
    }
}
