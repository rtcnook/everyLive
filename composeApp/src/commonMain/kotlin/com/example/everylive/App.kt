package com.example.everylive

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private val AppRed = Color(0xFFF2484F)
private val DarkRed = Color(0xFFD93D41)
private val AppGray = Color(0xFF8C8C8C)
private val LightGray = Color(0xFFEDEDED)
private val PageBackground = Color(0xFFFAFAFA)

private val EveryLiveLightScheme = lightColorScheme(
    primary = AppRed,
    secondary = DarkRed,
    background = PageBackground,
    surface = Color.White,
    surfaceVariant = Color(0xFFF3F3F3),
    onPrimary = Color.White,
    onBackground = Color(0xFF6F6F6F),
    onSurface = Color(0xFF6F6F6F),
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
        delay(600)
    }
}

data class AppUiState(
    val isLoading: Boolean = true,
)

private enum class MainTab(
    val label: String,
) {
    Home("首页"),
    Live("直播"),
    Follow("关注"),
    Mine("我的"),
}

private data class MineMenuItem(
    val icon: String,
    val title: String,
)

private val mineMenuItems = listOf(
    MineMenuItem("★", "我的星光"),
    MineMenuItem("☰", "星光贡献榜"),
    MineMenuItem("◷", "观看历史"),
    MineMenuItem("◌", "我的等级"),
    MineMenuItem("➤", "任务中心"),
    MineMenuItem("☍", "游戏中心"),
    MineMenuItem("⚙", "设置"),
)

@Composable
@Preview
fun App() {
    val appViewModel = viewModel { AppViewModel() }
    val uiState by appViewModel.uiState.collectAsState()

    MaterialTheme(colorScheme = EveryLiveLightScheme) {
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
            .background(Color.White)
            .safeContentPadding(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BrandLogo(modifier = Modifier.height(58.dp))
            Spacer(Modifier.height(18.dp))
            Text("正在初始化...", color = AppGray, fontSize = 18.sp)
        }
    }
}

@Composable
private fun EveryLiveMainScreen() {
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

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground),
    ) {
        HomeTopBar()
        Box(modifier = Modifier.fillMaxSize().background(PageBackground))
    }
}

@Composable
private fun LiveScreen() {
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
private fun FollowScreen() {
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
private fun MineScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        item { MineHeader() }
        item { WalletRow() }
        itemsIndexed(mineMenuItems) { index, item ->
            if (index == 1 || index == 5) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(22.dp)
                        .background(LightGray),
                )
            }
            MineMenuRow(item)
        }
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(LightGray),
            )
        }
    }
}

@Composable
private fun HomeTopBar() {
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
private fun TitleTopBar(title: String) {
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

@Composable
private fun MineHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(318.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFFFF6E73), AppRed, DarkRed),
                    center = Offset(560f, 120f),
                    radius = 820f,
                ),
            ),
    ) {
        HalftonePattern(modifier = Modifier.matchParentSize())
        PencilIcon(
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 14.dp),
            color = Color.White,
        )
        MailIcon(
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.TopEnd)
                .padding(end = 18.dp, top = 18.dp),
            color = Color.White,
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(114.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E2E2))
                    .border(3.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text("↯", color = Color(0xFF969696), fontSize = 62.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(34.dp))
            Text("点击登录", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(76.dp)
                .background(DarkRed.copy(alpha = 0.72f)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MineStat(modifier = Modifier.weight(1f), label = "关注")
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(42.dp)
                    .background(Color(0xFFB92B30)),
            )
            MineStat(modifier = Modifier.weight(1f), label = "粉丝")
        }
    }
}

@Composable
private fun MineStat(modifier: Modifier, label: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("0", color = Color.White, fontSize = 26.sp)
        Text(label, color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun WalletRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("🪙", fontSize = 30.sp)
        Text("0", modifier = Modifier.padding(start = 14.dp), color = Color(0xFF666666), fontSize = 25.sp)
        Spacer(Modifier.width(34.dp))
        Text("🍏", fontSize = 30.sp)
        Text("0", modifier = Modifier.padding(start = 14.dp), color = Color(0xFF666666), fontSize = 25.sp)
        Spacer(Modifier.weight(1f))
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .height(48.dp)
                .width(112.dp),
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color(0xFFC95B62)),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFC95B62)),
        ) {
            Text("充值", fontSize = 22.sp)
        }
    }
    HorizontalDivider(color = LightGray)
}

@Composable
private fun MineMenuRow(item: MineMenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(Color.White)
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(item.icon, color = AppGray, fontSize = 34.sp)
        Text(
            text = item.title,
            modifier = Modifier.padding(start = 22.dp),
            color = Color(0xFF777777),
            fontSize = 26.sp,
        )
        Spacer(Modifier.weight(1f))
        Text("›", color = AppGray, fontSize = 36.sp)
    }
    HorizontalDivider(color = LightGray, modifier = Modifier.padding(start = 30.dp))
}

@Composable
private fun BottomTabs(selectedTab: MainTab, onSelect: (MainTab) -> Unit) {
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
private fun BottomTabIcon(tab: MainTab, selected: Boolean, color: Color) {
    when (tab) {
        MainTab.Home -> HomeIcon(Modifier.fillMaxSize(), color)
        MainTab.Live -> TvIcon(Modifier.fillMaxSize(), color)
        MainTab.Follow -> HeartIcon(Modifier.fillMaxSize(), color)
        MainTab.Mine -> UserCircleIcon(Modifier.fillMaxSize(), color, selected)
    }
}

@Composable
private fun MailFab() {
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
private fun BrandLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(38.dp), contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stroke = Stroke(width = 5.dp.toPx(), join = StrokeJoin.Round)
                val path = Path().apply {
                    moveTo(size.width * 0.08f, size.height * 0.18f)
                    lineTo(size.width * 0.72f, size.height * 0.18f)
                    lineTo(size.width * 0.72f, size.height * 0.72f)
                    lineTo(size.width * 0.08f, size.height * 0.72f)
                    close()
                }
                drawPath(path, AppRed, style = stroke)
                val play = Path().apply {
                    moveTo(size.width * 0.36f, size.height * 0.33f)
                    lineTo(size.width * 0.58f, size.height * 0.45f)
                    lineTo(size.width * 0.36f, size.height * 0.58f)
                    close()
                }
                drawPath(play, AppRed)
            }
        }
        Text("全民直播", color = AppRed, fontSize = 30.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun SearchIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = color,
            radius = size.minDimension * 0.31f,
            center = Offset(size.width * 0.42f, size.height * 0.42f),
            style = Stroke(width = 4.dp.toPx()),
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.64f, size.height * 0.64f),
            end = Offset(size.width * 0.9f, size.height * 0.9f),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun MailIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 3.4.dp.toPx(), join = StrokeJoin.Round)
        val left = size.width * 0.12f
        val top = size.height * 0.22f
        val right = size.width * 0.88f
        val bottom = size.height * 0.78f
        drawRect(color, topLeft = Offset(left, top), size = androidx.compose.ui.geometry.Size(right - left, bottom - top), style = stroke)
        drawLine(color, Offset(left, top), Offset(size.width * 0.5f, size.height * 0.55f), stroke.width, cap = StrokeCap.Round)
        drawLine(color, Offset(right, top), Offset(size.width * 0.5f, size.height * 0.55f), stroke.width, cap = StrokeCap.Round)
    }
}

@Composable
private fun MenuIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        repeat(3) { index ->
            val y = size.height * (0.25f + index * 0.25f)
            drawLine(color, Offset(size.width * 0.1f, y), Offset(size.width * 0.9f, y), 4.dp.toPx(), cap = StrokeCap.Round)
        }
    }
}

@Composable
private fun HomeIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val roof = Path().apply {
            moveTo(size.width * 0.1f, size.height * 0.48f)
            lineTo(size.width * 0.5f, size.height * 0.12f)
            lineTo(size.width * 0.9f, size.height * 0.48f)
            lineTo(size.width * 0.78f, size.height * 0.48f)
            lineTo(size.width * 0.78f, size.height * 0.88f)
            lineTo(size.width * 0.58f, size.height * 0.88f)
            lineTo(size.width * 0.58f, size.height * 0.62f)
            lineTo(size.width * 0.42f, size.height * 0.62f)
            lineTo(size.width * 0.42f, size.height * 0.88f)
            lineTo(size.width * 0.22f, size.height * 0.88f)
            lineTo(size.width * 0.22f, size.height * 0.48f)
            close()
        }
        drawPath(roof, color)
    }
}

@Composable
private fun TvIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 4.dp.toPx(), join = StrokeJoin.Round, cap = StrokeCap.Round)
        drawRoundRect(color, topLeft = Offset(size.width * 0.18f, size.height * 0.26f), size = androidx.compose.ui.geometry.Size(size.width * 0.64f, size.height * 0.58f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx()), style = stroke)
        drawLine(color, Offset(size.width * 0.36f, size.height * 0.26f), Offset(size.width * 0.18f, size.height * 0.08f), 4.dp.toPx(), cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.64f, size.height * 0.26f), Offset(size.width * 0.82f, size.height * 0.08f), 4.dp.toPx(), cap = StrokeCap.Round)
        val play = Path().apply {
            moveTo(size.width * 0.43f, size.height * 0.43f)
            lineTo(size.width * 0.61f, size.height * 0.55f)
            lineTo(size.width * 0.43f, size.height * 0.67f)
            close()
        }
        drawPath(play, color)
    }
}

@Composable
private fun HeartIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.84f)
            cubicTo(size.width * 0.15f, size.height * 0.58f, size.width * 0.1f, size.height * 0.34f, size.width * 0.28f, size.height * 0.22f)
            cubicTo(size.width * 0.4f, size.height * 0.14f, size.width * 0.5f, size.height * 0.24f, size.width * 0.5f, size.height * 0.34f)
            cubicTo(size.width * 0.5f, size.height * 0.24f, size.width * 0.6f, size.height * 0.14f, size.width * 0.72f, size.height * 0.22f)
            cubicTo(size.width * 0.9f, size.height * 0.34f, size.width * 0.85f, size.height * 0.58f, size.width * 0.5f, size.height * 0.84f)
            close()
        }
        drawPath(path, color)
    }
}

@Composable
private fun UserCircleIcon(modifier: Modifier, color: Color, selected: Boolean) {
    Canvas(modifier = modifier) {
        val strokeWidth = if (selected) 4.dp.toPx() else 3.5.dp.toPx()
        drawCircle(color, radius = size.minDimension * 0.42f, center = Offset(size.width / 2, size.height / 2), style = Stroke(width = strokeWidth))
        drawCircle(color, radius = size.minDimension * 0.14f, center = Offset(size.width / 2, size.height * 0.43f))
        drawOval(color, topLeft = Offset(size.width * 0.28f, size.height * 0.58f), size = androidx.compose.ui.geometry.Size(size.width * 0.44f, size.height * 0.2f))
    }
}

@Composable
private fun UserLoginArt(modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Brush.radialGradient(listOf(Color(0xFFFFC8C9), Color(0xFFFF8D91)))),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(100.dp)) {
            drawCircle(Color(0xFFFFCACC), radius = size.width * 0.2f, center = Offset(size.width / 2, size.height * 0.34f))
            drawOval(Color(0xFFFFCACC), topLeft = Offset(size.width * 0.14f, size.height * 0.58f), size = androidx.compose.ui.geometry.Size(size.width * 0.72f, size.height * 0.36f))
        }
    }
}

@Composable
private fun LiveFailArt(modifier: Modifier) {
    Canvas(modifier = modifier) {
        val pale = Color(0xFFE7E7E7)
        drawCircle(pale.copy(alpha = 0.7f), radius = size.minDimension * 0.34f, center = Offset(size.width * 0.44f, size.height * 0.62f))
        drawRect(pale, topLeft = Offset(size.width * 0.34f, size.height * 0.15f), size = androidx.compose.ui.geometry.Size(size.width * 0.48f, size.height * 0.42f))
        drawLine(Color.White, Offset(size.width * 0.45f, size.height * 0.33f), Offset(size.width * 0.7f, size.height * 0.33f), 5.dp.toPx(), cap = StrokeCap.Round)
        drawLine(Color.White, Offset(size.width * 0.5f, size.height * 0.43f), Offset(size.width * 0.65f, size.height * 0.43f), 5.dp.toPx(), cap = StrokeCap.Round)
    }
}

@Composable
private fun PencilIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(color, Offset(size.width * 0.2f, size.height * 0.82f), Offset(size.width * 0.78f, size.height * 0.24f), 4.dp.toPx(), cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.64f, size.height * 0.1f), Offset(size.width * 0.9f, size.height * 0.36f), 4.dp.toPx(), cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.12f, size.height * 0.9f), Offset(size.width * 0.28f, size.height * 0.82f), 4.dp.toPx(), cap = StrokeCap.Round)
    }
}

@Composable
private fun HalftonePattern(modifier: Modifier) {
    Canvas(modifier = modifier) {
        val dotColor = Color(0xFFB6272E).copy(alpha = 0.18f)
        val step = 18.dp.toPx()
        var x = -size.width * 0.1f
        while (x < size.width * 1.1f) {
            var y = 0f
            while (y < size.height) {
                val normalized = kotlin.math.abs(x - size.width / 2) / (size.width / 2)
                val radius = (2.5f + normalized * 4f).dp.toPx()
                drawCircle(dotColor, radius = radius, center = Offset(x, y))
                y += step
            }
            x += step
        }
    }
}
