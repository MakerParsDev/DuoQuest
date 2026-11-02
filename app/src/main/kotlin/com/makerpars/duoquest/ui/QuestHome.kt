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
import androidx.compose.ui.unit.*

@Composable
internal fun HomeContent(
    games: Int, streak: Int, best: Int, playedToday: Boolean,
    onDaily: () -> Unit, onDuo: () -> Unit, onStats: () -> Unit, onInvite: () -> Unit
) {
    val compact = compactScreen()
    BrandHeader()
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
        Tag("A LITTLE JOY, EVERY DAY", Mint)
        Text("✦", color = Violet, fontSize = 14.sp)
    }
    LargeTitle(
        "Make today a\ngood story.",
        "Quick games. Better conversations. Something worth sharing.",
        compact = compact
    )
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        StatTile("$streak", "DAY STREAK", Modifier.weight(1f))
        StatTile("$best / 5", "BEST SCORE", Modifier.weight(1f))
        StatTile("$games", "GAMES PLAYED", Modifier.weight(1f))
    }
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF8361DF), Color(0xFF635DB6), Color(0xFF304675))
                )
            )
            .border(1.dp, Color.White.copy(alpha = .2f), RoundedCornerShape(24.dp))
            .padding(if (compact) 17.dp else 22.dp),
        verticalArrangement = Arrangement.spacedBy(if (compact) 10.dp else 13.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Tag(if (playedToday) "COME BACK TOMORROW" else "FRESH TODAY", Mint)
            Text("✦", color = Mint, fontSize = 26.sp)
        }
        Text("The Daily Drop", color = Color.White, fontSize = (if (compact) 26 else 29).sp,
            fontWeight = FontWeight.Black, letterSpacing = (-.7).sp)
        Text("Five new questions. A quick win for your brain.",
            color = Color.White.copy(alpha = .9f), fontSize = 13.sp, lineHeight = 19.sp)
        SolidButton(if (playedToday) "Replay today's drop  →" else "Play today's drop  →",
            onClick = onDaily, accent = Mint)
    }
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text("PLAY WITH PEOPLE", color = Soft, fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp, fontSize = 11.sp)
        Text("02 MODES", color = Violet, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
    GlassCard {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Tag("PASS & PLAY • 5 ROUNDS")
            Text("♡", color = Mint, fontSize = 25.sp)
        }
        Text("Know Me Better", color = Snow, fontWeight = FontWeight.ExtraBold, fontSize = 23.sp)
        Text("Pick your answer. Pass the phone. Discover what your friend really knows.",
            color = Soft, fontSize = 13.sp, lineHeight = 19.sp)
        SolidButton("Start a duo quest  →", onClick = onDuo)
        OutlineButton("Invite a friend to play", onClick = onInvite)
    }
    GlassCard {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text("YOUR JOURNEY", color = Violet, fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp, fontSize = 10.sp)
                Text("Every round counts.", color = Snow, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text("Your scores stay right here.", color = Soft, fontSize = 12.sp)
            }
            TextButton(onClick = onStats) { Text("See stats  ↗", color = Mint, fontSize = 13.sp) }
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(7.dp).clip(CircleShape).background(Mint))
        Text("NO SIGN-UP • NO PRESSURE • JUST PLAY", color = Soft, fontSize = 10.sp, letterSpacing = .3.sp)
    }
    Spacer(Modifier.height(4.dp))
}

@Composable
internal fun StatsContent(games: Int, best: Int, streak: Int, matches: Int, onBack: () -> Unit) {
    BackLink(onBack)
    Tag("YOUR JOURNEY", Mint)
    LargeTitle("A little progress.\nA lot of fun.", "Each game is another reason to come back.")
    GlassCard {
        Text("YOUR HIGHLIGHTS", color = Violet, letterSpacing = 1.2.sp,
            fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            StatTile("$games", "GAMES PLAYED", Modifier.weight(1f))
            StatTile("$best / 5", "BEST DAILY", Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            StatTile("$streak", "DAY STREAK", Modifier.weight(1f))
            StatTile("$matches", "DUO MATCHES", Modifier.weight(1f))
        }
    }
    GlassCard {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SparkMark()
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("A new chance every day", color = Snow, fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold)
                Text("Try tomorrow's drop, or find out how well a friend knows you.",
                    color = Soft, fontSize = 13.sp, lineHeight = 19.sp)
            }
        }
    }
    OutlineButton("Back to your quests", onClick = onBack)
}
