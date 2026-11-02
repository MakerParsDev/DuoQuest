package com.parsfilo.duoquest.ui

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
    onDaily: () -> Unit, onDuo: () -> Unit, onStats: () -> Unit, onInvite: () -> Unit,
    language: String, onLanguageChange: () -> Unit
) {
    val compact = compactScreen()
    BrandHeader()
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        TextButton(onClick = onLanguageChange, modifier = Modifier.heightIn(min = 42.dp)) {
            Text(if (language == "tr") "English  ↗" else "Türkçe  ↗",
                color = Mint, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
        Tag(t("A LITTLE JOY, EVERY DAY"), Mint)
        Text("✦", color = Violet, fontSize = 14.sp)
    }
    LargeTitle(
        t("Make today a\ngood story."),
        t("Quick games. Better conversations. Something worth sharing."),
        compact = compact
    )
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        StatTile("$streak", t("DAY STREAK"), Modifier.weight(1f))
        StatTile("$best / 5", t("BEST SCORE"), Modifier.weight(1f))
        StatTile("$games", t("GAMES PLAYED"), Modifier.weight(1f))
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
            Tag(if (playedToday) t("COME BACK TOMORROW") else t("FRESH TODAY"), Mint)
            Text("✦", color = Mint, fontSize = 26.sp)
        }
        Text(t("The Daily Drop"), color = Color.White, fontSize = (if (compact) 26 else 29).sp,
            fontWeight = FontWeight.Black, letterSpacing = (-.7).sp)
        Text(t("Five new questions. A quick win for your brain."),
            color = Color.White.copy(alpha = .9f), fontSize = 13.sp, lineHeight = 19.sp)
        SolidButton(if (playedToday) t("Replay today's drop  →") else t("Play today's drop  →"),
            onClick = onDaily, accent = Mint)
    }
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(t("PLAY WITH PEOPLE"), color = Soft, fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp, fontSize = 11.sp)
        Text(t("02 MODES"), color = Violet, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
    GlassCard {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Tag(t("PASS & PLAY • 5 ROUNDS"))
            Text("♡", color = Mint, fontSize = 25.sp)
        }
        Text(t("Know Me Better"), color = Snow, fontWeight = FontWeight.ExtraBold, fontSize = 23.sp)
        Text(t("Pick your answer. Pass the phone. Discover what your friend really knows."),
            color = Soft, fontSize = 13.sp, lineHeight = 19.sp)
        SolidButton(t("Start a duo quest  →"), onClick = onDuo)
        OutlineButton(t("Invite a friend to play"), onClick = onInvite)
    }
    GlassCard {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(t("YOUR JOURNEY"), color = Violet, fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp, fontSize = 10.sp)
                Text(t("Every round counts."), color = Snow, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text(t("Your scores stay right here."), color = Soft, fontSize = 12.sp)
            }
            TextButton(onClick = onStats) { Text(t("See stats  ↗"), color = Mint, fontSize = 13.sp) }
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(7.dp).clip(CircleShape).background(Mint))
        Text(t("NO SIGN-UP • NO PRESSURE • JUST PLAY"), color = Soft, fontSize = 10.sp, letterSpacing = .3.sp)
    }
    Spacer(Modifier.height(4.dp))
}

@Composable
internal fun StatsContent(games: Int, best: Int, streak: Int, matches: Int, onBack: () -> Unit) {
    BackLink(onBack)
    Tag(t("YOUR JOURNEY"), Mint)
    LargeTitle(t("A little progress.\nA lot of fun."), t("Each game is another reason to come back."))
    GlassCard {
        Text(t("YOUR HIGHLIGHTS"), color = Violet, letterSpacing = 1.2.sp,
            fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            StatTile("$games", t("GAMES PLAYED"), Modifier.weight(1f))
            StatTile("$best / 5", t("BEST DAILY"), Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            StatTile("$streak", t("DAY STREAK"), Modifier.weight(1f))
            StatTile("$matches", t("DUO MATCHES"), Modifier.weight(1f))
        }
    }
    GlassCard {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SparkMark()
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(t("A new chance every day"), color = Snow, fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold)
                Text(t("Try tomorrow's drop, or find out how well a friend knows you."),
                    color = Soft, fontSize = 13.sp, lineHeight = 19.sp)
            }
        }
    }
    OutlineButton(t("Back to your quests"), onClick = onBack)
}
