package com.makerpars.duoquest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.makerpars.duoquest.game.GameMode
import com.makerpars.duoquest.game.Quest
import com.makerpars.duoquest.game.QuestRules

@Composable
internal fun GameContent(
    mode: GameMode, quest: Quest, round: Int, isFirst: Boolean,
    onBack: () -> Unit, onAnswer: (Int) -> Unit
) {
    BackLink(onBack)
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Tag(when (mode) { GameMode.DAILY -> "✦ DAILY DROP"; GameMode.INVITE -> "♡ MAKE A CHALLENGE"; GameMode.REPLY -> "♡ FRIEND CHALLENGE"; GameMode.DUO -> "♡ KNOW ME BETTER" })
        Text("${round + 1} / 5", color = Soft, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
    ProgressBar(round)
    Spacer(Modifier.height(8.dp))
    LargeTitle(
        title = if (mode == GameMode.DAILY) "Think fast." else if (mode == GameMode.INVITE) "Your secret picks." else if (mode == GameMode.REPLY) "Can you guess?" else if (isFirst) "Your move." else "Can you guess?",
        subtitle = if (mode == GameMode.DAILY) "Pick the correct answer. Every point counts."
            else if (mode == GameMode.INVITE) "Choose five answers. Your friend will guess them on their phone."
            else if (mode == GameMode.REPLY) "Can you predict the five choices your friend made?"
            else if (isFirst) "Player 1 · Choose what feels most like you."
            else "Player 2 · Guess what your friend picked."
    )
    Spacer(Modifier.height(6.dp))
    GlassCard {
        Text("QUESTION ${round + 1}", color = Violet, letterSpacing = 1.6.sp, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Text(quest.prompt, color = Snow, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 33.sp)
    }
    quest.options.forEachIndexed { index, option ->
        OptionCard(index = index, text = option, onClick = { onAnswer(index) })
    }
    Text("Choose one answer to continue.", color = Soft, fontSize = 12.sp)
    Spacer(Modifier.height(10.dp))
}

@Composable
internal fun HandoffContent(round: Int, onReady: () -> Unit, onBack: () -> Unit) {
    BackLink(onBack)
    Tag("SECRET ANSWER LOCKED", Mint)
    Spacer(Modifier.height(35.dp))
    Text("↗", color = Violet, fontSize = 76.sp, fontWeight = FontWeight.Black)
    LargeTitle("Pass the\nphone.", "Player 1's choice is hidden. Give the phone to Player 2 before continuing.")
    GlassCard {
        Text("PLAYER 2, YOU'RE UP", color = Mint, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.6.sp)
        Text("Think you know your friend? You'll see the same question and try to predict their answer.",
            color = Snow, fontSize = 16.sp, lineHeight = 25.sp)
        Text("ROUND ${round + 1} OF 5", color = Soft, fontSize = 12.sp)
    }
    SolidButton("I'm Player 2 — let's guess  →", onClick = onReady, accent = Mint)
}

@Composable
internal fun FeedbackContent(
    mode: GameMode, quest: Quest, round: Int, score: Int,
    firstChoice: Int, guess: Int, onBack: () -> Unit, onContinue: () -> Unit
) {
    val correct = if (mode == GameMode.DAILY) guess == quest.answer else firstChoice == guess
    BackLink(onBack)
    Spacer(Modifier.height(26.dp))
    Text(if (correct) "✦" else "◇", color = if (correct) Mint else Amber,
        fontSize = 74.sp, fontWeight = FontWeight.Black)
    Tag(if (correct) "POINT EARNED" else "NEXT ONE'S YOURS", if (correct) Mint else Amber)
    LargeTitle(
        title = if (correct && mode == GameMode.DAILY) "Nice answer!" else if (correct) "Perfect match!" else if (mode == GameMode.DAILY) "Not this time." else "Almost in sync!",
        subtitle = if (mode == GameMode.DAILY) "Here's the correct answer." else "Now you know each other a little better."
    )
    GlassCard {
        Text(quest.prompt, color = Soft, fontSize = 14.sp, lineHeight = 21.sp)
        if (mode == GameMode.DUO || mode == GameMode.REPLY) {
            Text(if (mode == GameMode.REPLY) "Your friend chose:" else "Player 1 chose:", color = Violet, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text(quest.options[firstChoice], color = Snow, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(if (mode == GameMode.REPLY) "You guessed:" else "Player 2 guessed:", color = Violet, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text(quest.options[guess], color = if (correct) Mint else Amber, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        } else {
            Text(quest.options[quest.answer], color = Mint, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
    Text("$score POINT${if (score == 1) "" else "S"} SO FAR", color = Soft,
        fontWeight = FontWeight.Bold, fontSize = 13.sp)
    SolidButton(if (round == QuestRules.ROUND_COUNT - 1) "See my results  →" else "Next question  →",
        onClick = onContinue, accent = Mint)
}

@Composable
internal fun ResultContent(mode: GameMode, score: Int, onHome: () -> Unit, onPlayAgain: () -> Unit, onShare: () -> Unit) {
    BrandHeader()
    Spacer(Modifier.height(28.dp))
    Tag("QUEST COMPLETED", Mint)
    Text("✧", color = Amber, fontSize = 67.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    Text("$score / 5", color = Snow, fontSize = 74.sp, fontWeight = FontWeight.Black,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    Text(
        when (score) { 5 -> "Legendary energy."; 4 -> "You're on fire."; 3 -> "That was a good run."; else -> "The best part? Playing." },
        color = Snow, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
    )
    Text(
        if (mode != GameMode.DAILY) "Five questions. One stronger connection." else "Your daily brain workout is complete.",
        color = Soft, fontSize = 15.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
    )
    Spacer(Modifier.height(16.dp))
    SolidButton("Share your score  ↗", onClick = onShare, accent = Mint)
    OutlineButton(if (mode == GameMode.DAILY) "Replay daily drop" else if (mode == GameMode.REPLY) "Back to more quests" else "Another duo quest", onClick = onPlayAgain)
    OutlineButton("Back to home", onClick = onHome)
}

@Composable
internal fun InviteResultContent(onHome: () -> Unit, onShare: () -> Unit) {
    BrandHeader()
    Spacer(Modifier.height(24.dp))
    Tag("CHALLENGE READY", Mint)
    Text("✧", color = Violet, fontSize = 75.sp)
    LargeTitle("Now it's their\nturn.", "Your five choices are ready. Send your challenge to a friend and see how well they know you.")
    GlassCard {
        Text("HOW IT WORKS", color = Violet, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
        Text("1  Your friend opens your challenge link.\n2  They guess each secret answer.\n3  They share their match score back with you.",
            color = Snow, fontSize = 16.sp, lineHeight = 28.sp)
    }
    SolidButton("Invite a friend  ↗", onClick = onShare, accent = Mint)
    OutlineButton("Back to home", onClick = onHome)
}
