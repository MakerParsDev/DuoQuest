package com.makerpars.duoquest.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*

internal val Ink = Color(0xFF080B18)
internal val Panel = Color(0xFF191E37)
internal val Violet = Color(0xFFB49BFF)
internal val Mint = Color(0xFF81F2D4)
internal val Soft = Color(0xFFADB6D0)
internal val Snow = Color(0xFFF8F7FF)
internal val Amber = Color(0xFFFFD68B)
internal val Frame = Color(0xFF383E61)

@Composable
internal fun compactScreen(): Boolean = LocalConfiguration.current.screenHeightDp < 740

@Composable
internal fun QuestBackdrop(content: @Composable ColumnScope.() -> Unit) {
    val compact = compactScreen()
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF1E1D42), Ink, Color(0xFF0B1228))))
    ) {
        Box(
            Modifier.size(290.dp).align(Alignment.TopEnd).offset(x = 120.dp, y = (-105).dp)
                .background(Brush.radialGradient(listOf(Color(0x553C3B98), Color.Transparent)), CircleShape)
        )
        Column(
            Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = if (compact) 18.dp else 22.dp, vertical = if (compact) 8.dp else 16.dp),
            verticalArrangement = Arrangement.spacedBy(if (compact) 11.dp else 16.dp),
            content = content
        )
    }
}

@Composable
internal fun BrandHeader() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("duo", color = Snow, fontSize = 25.sp, fontWeight = FontWeight.Black, letterSpacing = (-1).sp)
        Text("quest.", color = Mint, fontSize = 25.sp, fontWeight = FontWeight.Black, letterSpacing = (-1).sp,
            modifier = Modifier.weight(1f))
        SparkMark()
    }
}

@Composable
internal fun SparkMark() {
    Canvas(Modifier.size(40.dp)) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r = size.minDimension * .35f
        val shape = Path().apply {
            moveTo(cx, cy - r)
            quadraticTo(cx + r * .2f, cy - r * .2f, cx + r, cy)
            quadraticTo(cx + r * .2f, cy + r * .2f, cx, cy + r)
            quadraticTo(cx - r * .2f, cy + r * .2f, cx - r, cy)
            quadraticTo(cx - r * .2f, cy - r * .2f, cx, cy - r)
        }
        drawPath(shape, brush = Brush.linearGradient(listOf(Violet, Mint)))
        drawCircle(Mint, radius = 2.dp.toPx(), center = androidx.compose.ui.geometry.Offset(cx + r, cy - r))
    }
}

@Composable
internal fun Tag(text: String, color: Color = Violet) {
    Box(
        Modifier.clip(RoundedCornerShape(100))
            .background(color.copy(alpha = 0.13f))
            .border(1.dp, color.copy(alpha = 0.35f), RoundedCornerShape(100))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(text, color = color, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = .6.sp,
            maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
internal fun LargeTitle(title: String, subtitle: String, compact: Boolean = compactScreen()) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, color = Snow, fontSize = (if (compact) 26 else 32).sp,
            fontWeight = FontWeight.ExtraBold, lineHeight = (if (compact) 31 else 37).sp,
            letterSpacing = (-.8).sp, modifier = Modifier.semantics { heading() })
        Text(subtitle, color = Soft, fontSize = (if (compact) 13 else 14).sp,
            lineHeight = (if (compact) 18 else 21).sp)
    }
}

@Composable
internal fun SolidButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, accent: Color = Violet) {
    Button(
        onClick = onClick, modifier = modifier.fillMaxWidth().heightIn(min = 52.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = Ink),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 11.dp)
    ) {
        Text(text, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, maxLines = 2)
    }
}

@Composable
internal fun OutlineButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(onClick, modifier = modifier.fillMaxWidth().heightIn(min = 50.dp),
        shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, Frame)) {
        Text(text, color = Snow, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
internal fun GlassCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(21.dp))
            .background(Brush.verticalGradient(listOf(Color(0xFF202542), Panel)))
            .border(1.dp, Frame.copy(alpha = 0.8f), RoundedCornerShape(21.dp))
            .padding(if (compactScreen()) 15.dp else 19.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp), content = content
    )
}

@Composable
internal fun StatTile(number: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.height(94.dp).clip(RoundedCornerShape(17.dp))
            .background(Panel.copy(alpha = 0.9f))
            .border(1.dp, Frame.copy(alpha = 0.8f), RoundedCornerShape(17.dp))
            .padding(horizontal = 10.dp, vertical = 13.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(number, color = Snow, fontSize = 21.sp, fontWeight = FontWeight.ExtraBold,
            maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(label, color = Soft, fontSize = 9.sp, fontWeight = FontWeight.SemiBold,
            letterSpacing = .2.sp, maxLines = 2, lineHeight = 12.sp)
    }
}

@Composable
internal fun ProgressBar(round: Int) {
    Row(Modifier.fillMaxWidth().semantics { progressBarRangeInfo = ProgressBarRangeInfo((round + 1).toFloat(), 0f..5f) },
        horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(5) { i ->
            val selected = i <= round
            val color by animateColorAsState(if (selected) Violet else Frame, tween(260), label = "round progress")
            Box(Modifier.weight(1f).height(5.dp).clip(RoundedCornerShape(5.dp)).background(color))
        }
    }
}

@Composable
internal fun OptionCard(index: Int, text: String, onClick: () -> Unit, highlight: Boolean = false) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) .975f else 1f, tween(110), label = "option press")
    val bg by animateColorAsState(
        if (highlight) Mint.copy(alpha = .14f) else if (pressed) Color(0xFF303658) else Panel,
        tween(140), label = "option surface"
    )
    Row(
        modifier = Modifier.fillMaxWidth().scale(scale)
            .heightIn(min = if (compactScreen()) 52.dp else 60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(bg)
            .border(1.dp, if (highlight) Mint else Frame, RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = interactionSource, indication = ripple(),
                role = Role.Button, onClickLabel = t("CHOOSE ANSWER") + " " + (index + 1) + ": " + text,
                onClick = onClick
            )
            .padding(horizontal = 13.dp, vertical = if (compactScreen()) 9.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(Modifier.size(31.dp).clip(RoundedCornerShape(10.dp))
            .background(if (highlight) Mint else Color(0xFF30375A)), contentAlignment = Alignment.Center) {
            Text(('A' + index).toString(), color = if (highlight) Ink else Violet, fontSize = 14.sp,
                fontWeight = FontWeight.Black)
        }
        Text(text, color = if (highlight) Mint else Snow, fontSize = 14.sp, lineHeight = 18.sp,
            fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f), maxLines = 3)
    }
}

@Composable
internal fun BackLink(onClick: () -> Unit) {
    TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp), modifier = Modifier.heightIn(min = 40.dp)) {
        Text(t("←  Back to home"), color = Soft, fontSize = 13.sp)
    }
}

@Composable
internal fun ScoreOrb(score: Int, accent: Color = Mint) {
    var started by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { started = true }
    val progress by animateFloatAsState(
        if (started) score / 5f else 0f, tween(800), label = "score reveal"
    )
    Box(Modifier.fillMaxWidth().height(if (compactScreen()) 178.dp else 226.dp),
        contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(if (compactScreen()) 162.dp else 210.dp)) {
            val thickness = 9.dp.toPx()
            drawCircle(Frame, style = Stroke(thickness))
            drawArc(
                brush = Brush.sweepGradient(listOf(Violet, accent, Violet)),
                startAngle = -90f, sweepAngle = progress * 360f, useCenter = false,
                style = Stroke(thickness, cap = StrokeCap.Round)
            )
            drawCircle(accent.copy(alpha = .13f), radius = size.minDimension * .41f)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$score / 5", fontSize = (if (compactScreen()) 42 else 55).sp,
                fontWeight = FontWeight.Black, color = Snow)
            Text(t("YOUR SCORE"), color = accent, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.6.sp)
        }
    }
}
