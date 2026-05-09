package com.example.everylive

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
internal fun MineScreen() {
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
