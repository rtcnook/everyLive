package com.example.everylive

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private val EveryLiveDarkScheme = darkColorScheme(
    primary = Color(0xFFFF4D6D),
    secondary = Color(0xFFFFB703),
    tertiary = Color(0xFF5DD9C1),
    background = Color(0xFF111218),
    surface = Color(0xFF1A1B22),
    surfaceVariant = Color(0xFF252733),
    onPrimary = Color.White,
    onSecondary = Color(0xFF1C1B1F),
    onBackground = Color.White,
    onSurface = Color.White,
)

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
        delay(1_200)
    }
}

data class AppUiState(
    val isLoading: Boolean = true,
)

private enum class MainTab(
    val label: String,
    val icon: String,
) {
    Home("首页", "⌂"),
    Live("直播", "▶"),
    Follow("关注", "♡"),
    Mine("我的", "人"),
}

private data class FeedItem(
    val title: String,
    val creator: String,
    val tag: String,
    val heat: String,
    val accent: Color,
)

private data class LiveRoom(
    val title: String,
    val anchor: String,
    val viewers: String,
    val category: String,
    val accent: Color,
)

private data class ProfileAction(
    val title: String,
    val subtitle: String,
)

private val hotSearchItems = listOf(
    FeedItem("城市夜跑直播间突然爆火", "同城热榜", "热搜", "128.6w", Color(0xFFFF4D6D)),
    FeedItem("春日穿搭挑战赛", "潮流观察员", "挑战", "92.4w", Color(0xFFFFB703)),
    FeedItem("深夜电台：今天也要好好生活", "小满 FM", "治愈", "65.8w", Color(0xFF5DD9C1)),
    FeedItem("新歌首唱现场回放", "音乐现场", "音乐", "51.2w", Color(0xFF8E7CFF)),
)

private val recommendItems = listOf(
    FeedItem("根据你的观看偏好推荐：户外露营", "阿森在路上", "算法推荐", "36.1w", Color(0xFF42A5F5)),
    FeedItem("三分钟学会直播间布光", "设备研究所", "教程", "18.7w", Color(0xFFFF7043)),
    FeedItem("萌宠午休观察", "猫咪便利店", "宠物", "74.9w", Color(0xFFAED581)),
    FeedItem("今日游戏高能片段合集", "全能玩家", "游戏", "88.0w", Color(0xFFBA68C8)),
)

private val liveRooms = listOf(
    LiveRoom("海边日落陪你下班", "橙子", "12.8w", "户外", Color(0xFFFF8A65)),
    LiveRoom("峡谷五排冲分中", "北川", "8.4w", "游戏", Color(0xFF7986CB)),
    LiveRoom("点歌台营业到零点", "阿遥", "5.6w", "音乐", Color(0xFFF06292)),
    LiveRoom("一起做晚饭", "小青", "3.2w", "生活", Color(0xFF4DB6AC)),
    LiveRoom("新手健身答疑", "卡卡教练", "2.9w", "运动", Color(0xFFFFB74D)),
    LiveRoom("二次元闲聊大会", "米粒", "6.7w", "聊天", Color(0xFF9575CD)),
)

private val followedVideos = listOf(
    FeedItem("你关注的阿森更新了旅行 vlog", "阿森在路上", "已关注", "18分钟前", Color(0xFF42A5F5)),
    FeedItem("猫咪便利店发布了新视频", "猫咪便利店", "已关注", "42分钟前", Color(0xFFAED581)),
    FeedItem("设备研究所：直播收音避坑", "设备研究所", "已关注", "2小时前", Color(0xFFFF7043)),
)

private val profileActions = listOf(
    ProfileAction("账号信息", "昵称、头像、手机号与实名认证"),
    ProfileAction("App 设置", "通知、播放、清晰度和青少年模式"),
    ProfileAction("隐私设置", "关注列表、浏览记录和黑名单"),
    ProfileAction("清理缓存", "释放图片、视频预加载缓存"),
)

@Composable
@Preview
fun App() {
    val appViewModel = viewModel { AppViewModel() }
    val uiState by appViewModel.uiState.collectAsState()

    MaterialTheme(colorScheme = EveryLiveDarkScheme) {
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

@Composable
private fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF21111F), Color(0xFF111218)),
                ),
            )
            .safeContentPadding()
            .padding(28.dp),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFFFF4D6D), Color(0xFFFFB703)),
                        ),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text("全", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Black)
            }
            Text("全面直播", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text("正在初始化配置、账号和推荐流", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.72f))
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth(0.62f))
        }
        Text(
            text = "EveryLive",
            modifier = Modifier.align(Alignment.BottomCenter),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.44f),
        )
    }
}

@Composable
private fun EveryLiveMainScreen() {
    var selectedTab by remember { mutableIntStateOf(MainTab.Home.ordinal) }
    val currentTab = MainTab.entries[selectedTab]

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                MainTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = currentTab == tab,
                        onClick = { selectedTab = tab.ordinal },
                        icon = { Text(tab.icon, fontWeight = FontWeight.Bold) },
                        label = { Text(tab.label) },
                    )
                }
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
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

@Composable
private fun HomeScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("热搜", "算法推荐")

    Column(modifier = Modifier.fillMaxSize()) {
        PageHeader(title = "首页", subtitle = "发现正在发生的精彩内容")
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) },
                )
            }
        }
        val items = if (selectedTab == 0) hotSearchItems else recommendItems
        FeedList(items = items)
    }
}

@Composable
private fun LiveScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        PageHeader(title = "直播", subtitle = "推荐主播正在开播")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(liveRooms) { room ->
                LiveCard(room)
            }
        }
    }
}

@Composable
private fun FollowScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        PageHeader(title = "关注", subtitle = "你关注的博主新动态")
        FeedList(items = followedVideos)
    }
}

@Composable
private fun MineScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(26.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Brush.linearGradient(listOf(Color(0xFFFF4D6D), Color(0xFF8E7CFF)))),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("我", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.width(14.dp))
                    Column {
                        Text("全面直播用户", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("账号信息 · 创作者中心 · 钱包", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f))
                    }
                }
            }
        }
        items(profileActions) { action ->
            ProfileActionRow(action)
        }
    }
}

@Composable
private fun PageHeader(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp),
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(subtitle, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f))
    }
}

@Composable
private fun FeedList(items: List<FeedItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items) { item ->
            FeedCard(item)
        }
    }
}

@Composable
private fun FeedCard(item: FeedItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(22.dp),
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Thumbnail(accent = item.accent, modifier = Modifier.size(86.dp))
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Badge(containerColor = item.accent) { Text(item.tag) }
                    Spacer(Modifier.width(8.dp))
                    Text(item.heat, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.58f))
                }
                Text(item.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(item.creator, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.68f))
            }
        }
    }
}

@Composable
private fun LiveCard(room: LiveRoom) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(22.dp),
    ) {
        Column {
            Box {
                Thumbnail(accent = room.accent, modifier = Modifier.fillMaxWidth().aspectRatio(0.86f))
                Badge(
                    modifier = Modifier.padding(10.dp).align(Alignment.TopStart),
                    containerColor = Color(0xFFFF1744),
                ) {
                    Text("LIVE")
                }
                Text(
                    text = room.viewers,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Black.copy(alpha = 0.48f))
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(room.title, maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold)
                Text("${room.anchor} · ${room.category}", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.62f), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun Thumbnail(accent: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(accent, accent.copy(alpha = 0.45f), Color(0xFF20222C)),
                ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text("▶", style = MaterialTheme.typography.headlineMedium, color = Color.White.copy(alpha = 0.86f))
    }
}

@Composable
private fun ProfileActionRow(action: ProfileAction) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(action.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(5.dp))
            Text(action.subtitle, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.62f))
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
        }
    }
}
