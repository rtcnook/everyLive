package com.example.everylive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
internal fun EveryLiveMainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentTab = MainTab.fromRoute(navBackStackEntry?.destination?.route)

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
                onSelect = { tab ->
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PageBackground),
        ) {
            NavHost(
                navController = navController,
                startDestination = MainTab.Home.route,
                modifier = Modifier.fillMaxSize(),
            ) {
                composable(MainTab.Home.route) { HomeScreen() }
                composable(MainTab.Live.route) { LiveScreen() }
                composable(MainTab.Follow.route) { FollowScreen() }
                composable(MainTab.Mine.route) { MineScreen() }
            }
        }
    }
}
