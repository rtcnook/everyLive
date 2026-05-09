package com.example.everylive

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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

@Composable
@Preview
internal fun BrandLogo(modifier: Modifier = Modifier) {
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
        Text("大家直播", color = AppRed, fontSize = 30.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
internal fun SearchIcon(modifier: Modifier, color: Color) {
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
internal fun MailIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 3.4.dp.toPx(), join = StrokeJoin.Round)
        val left = size.width * 0.12f
        val top = size.height * 0.22f
        val right = size.width * 0.88f
        val bottom = size.height * 0.78f
        drawRect(
            color = color,
            topLeft = Offset(left, top),
            size = Size(right - left, bottom - top),
            style = stroke,
        )
        drawLine(
            color = color,
            start = Offset(left, top),
            end = Offset(size.width * 0.5f, size.height * 0.55f),
            strokeWidth = stroke.width,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(right, top),
            end = Offset(size.width * 0.5f, size.height * 0.55f),
            strokeWidth = stroke.width,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
@Preview
internal fun MenuIcon(modifier: Modifier = Modifier.size(34.dp), color: Color = Color(0xFF9E9E9E), ) {
    Canvas(modifier = modifier) {
        repeat(3) { index ->
            val y = size.height * (0.25f + index * 0.25f)
            drawLine(
                color = color,
                start = Offset(size.width * 0.1f, y),
                end = Offset(size.width * 0.9f, y),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round,
            )
        }
    }
}

@Composable
internal fun HomeIcon(modifier: Modifier, color: Color) {
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
internal fun TvIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 4.dp.toPx(), join = StrokeJoin.Round, cap = StrokeCap.Round)
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.18f, size.height * 0.26f),
            size = Size(size.width * 0.64f, size.height * 0.58f),
            cornerRadius = CornerRadius(4.dp.toPx()),
            style = stroke,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.36f, size.height * 0.26f),
            end = Offset(size.width * 0.18f, size.height * 0.08f),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.64f, size.height * 0.26f),
            end = Offset(size.width * 0.82f, size.height * 0.08f),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
        )
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
internal fun HeartIcon(modifier: Modifier, color: Color) {
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
internal fun UserCircleIcon(modifier: Modifier, color: Color, selected: Boolean) {
    Canvas(modifier = modifier) {
        val strokeWidth = if (selected) 4.dp.toPx() else 3.5.dp.toPx()
        drawCircle(
            color = color,
            radius = size.minDimension * 0.42f,
            center = Offset(size.width / 2, size.height / 2),
            style = Stroke(width = strokeWidth),
        )
        drawCircle(
            color = color,
            radius = size.minDimension * 0.14f,
            center = Offset(size.width / 2, size.height * 0.43f),
        )
        drawOval(
            color = color,
            topLeft = Offset(size.width * 0.28f, size.height * 0.58f),
            size = Size(size.width * 0.44f, size.height * 0.2f),
        )
    }
}

@Composable
internal fun UserLoginArt(modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Brush.radialGradient(listOf(Color(0xFFFFC8C9), Color(0xFFFF8D91)))),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(100.dp)) {
            drawCircle(
                color = Color(0xFFFFCACC),
                radius = size.width * 0.2f,
                center = Offset(size.width / 2, size.height * 0.34f),
            )
            drawOval(
                color = Color(0xFFFFCACC),
                topLeft = Offset(size.width * 0.14f, size.height * 0.58f),
                size = Size(size.width * 0.72f, size.height * 0.36f),
            )
        }
    }
}

@Composable
internal fun LiveFailArt(modifier: Modifier) {
    Canvas(modifier = modifier) {
        val pale = Color(0xFFE7E7E7)
        drawCircle(
            color = pale.copy(alpha = 0.7f),
            radius = size.minDimension * 0.34f,
            center = Offset(size.width * 0.44f, size.height * 0.62f),
        )
        drawRect(
            color = pale,
            topLeft = Offset(size.width * 0.34f, size.height * 0.15f),
            size = Size(size.width * 0.48f, size.height * 0.42f),
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.45f, size.height * 0.33f),
            end = Offset(size.width * 0.7f, size.height * 0.33f),
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.5f, size.height * 0.43f),
            end = Offset(size.width * 0.65f, size.height * 0.43f),
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round,
        )
    }
}

@Composable
internal fun PencilIcon(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(
            color = color,
            start = Offset(size.width * 0.2f, size.height * 0.82f),
            end = Offset(size.width * 0.78f, size.height * 0.24f),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.64f, size.height * 0.1f),
            end = Offset(size.width * 0.9f, size.height * 0.36f),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.12f, size.height * 0.9f),
            end = Offset(size.width * 0.28f, size.height * 0.82f),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
        )
    }
}

@Composable
internal fun HalftonePattern(modifier: Modifier) {
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
