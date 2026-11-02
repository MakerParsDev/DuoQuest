package com.makerpars.duoquest.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
internal fun HomeContent(
    games: Int, streak: Int, best: Int, playedToday: Boolean,
    onDaily: () -> Unit, onDuo: () -> Unit, onStats: () -> Unit, onInvite: () -> Unit
) {
    BrandHeader()
    Tag("YOUR DAILY SPARK")
    LargeTitle("Make today a\ngood story.", "Tiny challenges. Big conversations. More reasons to connect.")
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        StatTile("$streak", "DAY STREAK", Modifier.weight(1f))
        StatTile("$best / 5", "BEST SCORE", Modifier.weight(1f))
        StatTile("$games", "GAMES PLAYED", Modifier.weight(1f))
    }
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(28.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF8A69ED), Color(0xFF5753B9), Color(0xFF304175))))
            .padding(23.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Tag(if (playedToday) "TODAY COMPLETED" else "NEW TODAY", Mint)
            Text("✧", color = Color.White.copy(alpha = 0.8f), fontSize = 38.sp)
        }
        Text("The Daily\nDrop", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black, lineHeight = 34.sp)
        Text("Five quick questions. One new chance every day. How far can you go?",
            color = Color.White.copy(alpha = 0.88f), fontSize = 14.sp, lineHeight = 21.sp)
        SolidButton(if (playedToday) "Replay today's drop  →" else "Play today's drop  →",
            onClick = onDaily, accent = Mint)
    }
    Text("BETTER TOGETHER", color = Soft, fontWeight = FontWeight.Bold, letterSpacing = 1.7.sp, fontSize = 11.sp)
    GlassCard {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Tag("2 PLAYERS · 5 ROUNDS")
            Text("♡", color = Violet, fontSize = 29.sp)
        }
        Text("Know Me Better", color = Snow, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        Text("Choose your answer. Pass the phone. Can your friend guess you right?",
            color = Soft, fontSize = 14.sp, lineHeight = 20.sp)
        SolidButton("Start a duo quest  →", onClick = onDuo)
        OutlineButton("Invite a friend to play", onClick = onInvite)
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text("Your journey", color = Snow, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        TextButton(onClick = onStats) { Text("View stats  ↗", color = Mint) }
    }
    Text("No sign-up. No pressure. Just play.", color = Soft, fontSize = 12.sp)
    Spacer(Modifier.height(9.dp))
}

@Composable
internal fun StatsContent(games: Int, best: Int, streak: Int, matches: Int, onBack: () -> Unit) {
    BackLink(onBack)
    Tag("YOUR PROGRESS")
    LargeTitle("Every round\ncounts.", "Your results stay on this device. Play again to level up.")
    GlassCard {
        Text("ALL-TIME STATS", color = Violet, letterSpacing = 1.5.sp, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatTile("$games", "FINISHED GAMES", Modifier.weight(1f))
            StatTile("$best / 5", "BEST DAILY", Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatTile("$streak", "DAY STREAK", Modifier.weight(1f))
            StatTile("$matches", "MATCHED ANSWERS", Modifier.weight(1f))
        }
    }
    GlassCard {
        Text("✦  Keep the spark alive", color = Mint, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Play the daily drop and invite a friend for a five-round duo quest.",
            color = Soft, fontSize = 14.sp, lineHeight = 21.sp)
    }
    OutlineButton("Back to your quests", onClick = onBack)
}
