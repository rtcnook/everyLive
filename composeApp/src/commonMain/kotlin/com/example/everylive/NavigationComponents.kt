package com.example.everylive

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun BottomTabs(selectedTab: MainTab, onSelect: (MainTab) -> Unit) {
    Surface(color = Color.White, shadowElevation = 4.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MainTab.entries.forEach { tab ->
                val selected = selectedTab == tab
                val color = if (selected) AppRed else Color(0xFFA7A7A7)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSelect(tab) }
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier.size(36.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        BottomTabIcon(tab = tab, selected = selected, color = color)
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(tab.label, color = color, fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
internal fun MailFab() {
    FloatingActionButton(
        onClick = {},
        modifier = Modifier.size(78.dp),
        shape = CircleShape,
        containerColor = AppRed,
        contentColor = Color.White,
    ) {
        MailIcon(modifier = Modifier.size(38.dp), color = Color.White)
    }
}

@Composable
private fun BottomTabIcon(tab: MainTab, selected: Boolean, color: Color) {
    when (tab) {
        MainTab.Home -> HomeIcon(Modifier.fillMaxSize(), color)
        MainTab.Live -> TvIcon(Modifier.fillMaxSize(), color)
        MainTab.Follow -> HeartIcon(Modifier.fillMaxSize(), color)
        MainTab.Mine -> UserCircleIcon(Modifier.fillMaxSize(), color, selected)
    }
}
