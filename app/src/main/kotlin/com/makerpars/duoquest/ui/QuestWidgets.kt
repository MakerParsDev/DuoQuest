package com.makerpars.duoquest.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

internal val Ink = Color(0xFF080B18)
internal val Panel = Color(0xFF171C34)
internal val Violet = Color(0xFFA78BFA)
internal val Mint = Color(0xFF78F0CF)
internal val Soft = Color(0xFFA6ADC7)
internal val Snow = Color(0xFFF6F4FF)
internal val Amber = Color(0xFFFFCE79)
internal val Frame = Color(0xFF343A5A)

@Composable
internal fun QuestBackdrop(content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF15152E), Ink, Color(0xFF0B1225))))
    ) {
        Box(
            Modifier.size(370.dp).offset(x = 155.dp, y = (-125).dp)
                .background(Brush.radialGradient(listOf(Color(0x553F3A9A), Color.Transparent)), CircleShape)
        )
        Column(
            Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            content = content
        )
    }
}

@Composable
internal fun BrandHeader() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text("duo", color = Snow, fontSize = 26.sp, fontWeight = FontWeight.Black, letterSpacing = (-1).sp)
        Text("quest.", color = Mint, fontSize = 26.sp, fontWeight = FontWeight.Black, letterSpacing = (-1).sp,
            modifier = Modifier.weight(1f))
        Text("✦", color = Violet, fontSize = 28.sp)
    }
}

@Composable
internal fun Tag(text: String, color: Color = Violet) {
    Box(Modifier.clip(RoundedCornerShape(100)).background(color.copy(alpha = 0.13f))
        .border(1.dp, color.copy(alpha = 0.27f), RoundedCornerShape(100))
        .padding(horizontal = 12.dp, vertical = 7.dp)) {
        Text(text, color = color, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
    }
}

@Composable
internal fun LargeTitle(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, color = Snow, fontSize = 35.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 39.sp, letterSpacing = (-1).sp)
        Text(subtitle, color = Soft, fontSize = 14.sp, lineHeight = 21.sp)
    }
}

@Composable
internal fun SolidButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, accent: Color = Violet) {
    Button(
        onClick = onClick, modifier = modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = Ink)
    ) {
        Text(text, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
internal fun OutlineButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(onClick, modifier = modifier.fillMaxWidth().height(51.dp),
        shape = RoundedCornerShape(18.dp), border = BorderStroke(1.dp, Frame)) {
        Text(text, color = Snow, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
internal fun GlassCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp))
            .background(Panel).border(1.dp, Frame.copy(alpha = 0.75f), RoundedCornerShape(24.dp))
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp), content = content
    )
}

@Composable
internal fun StatTile(number: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(18.dp)).background(Panel.copy(alpha = 0.85f))
            .border(1.dp, Frame.copy(alpha = 0.5f), RoundedCornerShape(18.dp)).padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(number, color = Snow, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
        Text(label, color = Soft, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
internal fun ProgressBar(round: Int) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        repeat(5) { i ->
            Box(Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(6.dp))
                .background(if (i <= round) Violet else Frame))
        }
    }
}

@Composable
internal fun OptionCard(index: Int, text: String, onClick: () -> Unit, highlight: Boolean = false) {
    val color = if (highlight) Mint else Snow
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp))
            .background(if (highlight) Mint.copy(alpha = 0.12f) else Panel)
            .border(1.dp, if (highlight) Mint else Frame, RoundedCornerShape(18.dp))
            .clickable(onClick = onClick).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(Modifier.size(34.dp).clip(RoundedCornerShape(11.dp))
            .background(if (highlight) Mint else Color(0xFF2C3252)), contentAlignment = Alignment.Center) {
            Text(('A' + index).toString(), color = if (highlight) Ink else Violet, fontWeight = FontWeight.Black)
        }
        Text(text, color = color, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
    }
}

@Composable
internal fun BackLink(onClick: () -> Unit) {
    TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp)) {
        Text("←  Back to home", color = Soft, fontSize = 13.sp)
    }
}
