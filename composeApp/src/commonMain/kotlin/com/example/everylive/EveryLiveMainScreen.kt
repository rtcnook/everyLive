package com.example.everylive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
internal fun EveryLiveMainScreen() {
    var selectedTab by remember { mutableIntStateOf(MainTab.Home.ordinal) }
    val currentTab = MainTab.entries[selectedTab]

    Scaffold(
        containerColor = PageBackground,
        floatingActionButton = {
            if (currentTab == MainTab.Home || currentTab == MainTab.Mine) {
                MailFab()
            }
        },
        bottomBar = {
            BottomTabs(
                selectedTab = currentTab,
                onSelect = { selectedTab = it.ordinal },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PageBackground),
        ) {
            when (currentTab) {
                MainTab.Home -> HomeScreen()
                MainTab.Live -> LiveScreen()
                MainTab.Follow -> FollowScreen()
                MainTab.Mine -> MineScreen()
            }
        }
    }
}
