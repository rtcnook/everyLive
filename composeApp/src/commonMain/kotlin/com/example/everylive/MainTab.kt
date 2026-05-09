package com.example.everylive

internal enum class MainTab(
    val label: String,
    val route: String,
) {
    Home("首页", "home"),
    Live("直播", "live"),
    Follow("关注", "follow"),
    Mine("我的", "mine");

    companion object {
        fun fromRoute(route: String?): MainTab = entries.firstOrNull { it.route == route } ?: Home
    }
}
