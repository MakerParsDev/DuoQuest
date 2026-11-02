package com.makerpars.duoquest.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.scale
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
    val compact = compactScreen()
    BackLink(onBack)
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Tag(when (mode) {
            GameMode.DAILY -> t("✦ DAILY DROP")
            GameMode.INVITE -> t("♡ CREATE A CHALLENGE")
            GameMode.REPLY -> t("♡ FRIEND CHALLENGE")
            GameMode.DUO -> t("♡ KNOW ME BETTER")
        })
        Text(t("ROUND") + " " + (round + 1) + " / 5", color = Soft, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
    ProgressBar(round)
    LargeTitle(
        title = when (mode) {
            GameMode.DAILY -> t("Think fast.")
            GameMode.INVITE -> t("Your secret picks.")
            GameMode.REPLY -> t("Can you guess?")
            GameMode.DUO -> if (isFirst) t("Your move.") else t("Can you guess?")
        },
        subtitle = when (mode) {
            GameMode.DAILY -> t("One question. One point. Trust your instincts.")
            GameMode.INVITE -> t("Choose your answers. Your friend will try to guess.")
            GameMode.REPLY -> t("What would your friend pick?")
            GameMode.DUO -> if (isFirst) t("Player 1 · Pick what feels most like you.")
                else t("Player 2 · Guess what your friend picked.")
        },
        compact = compact
    )
    GlassCard {
        Text(t("QUESTION") + " " + (round + 1), color = Mint, letterSpacing = 1.2.sp,
            fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Text(quest.prompt, color = Snow, fontSize = (if (compact) 20 else 23).sp,
            fontWeight = FontWeight.ExtraBold, lineHeight = (if (compact) 25 else 29).sp)
    }
    quest.options.forEachIndexed { index, option ->
        OptionCard(index = index, text = option, onClick = { onAnswer(index) })
    }
    Text(if (mode == GameMode.DAILY) t("Tap an answer to lock it in.")
        else t("Go with your first instinct."), color = Soft, fontSize = 11.sp)
}

@Composable
internal fun HandoffContent(round: Int, onReady: () -> Unit, onBack: () -> Unit) {
    BackLink(onBack)
    Tag(t("ANSWER LOCKED"), Mint)
    Spacer(Modifier.height(if (compactScreen()) 6.dp else 22.dp))
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text("↗", color = Violet, fontSize = (if (compactScreen()) 62 else 76).sp,
            fontWeight = FontWeight.Black)
    }
    LargeTitle(t("Pass the phone."), t("Player 1's answer is hidden. Give the phone to Player 2."))
    GlassCard {
        Text(t("PLAYER 2, YOU'RE UP"), color = Mint, fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold, letterSpacing = 1.1.sp)
        Text(t("Can you read your friend's mind? The same question is waiting for you."),
            color = Snow, fontSize = 15.sp, lineHeight = 22.sp)
        Text(t("ROUND") + " " + (round + 1) + " " + t("OF") + " 5", color = Soft, fontSize = 11.sp)
    }
    SolidButton(t("I'm Player 2 — let's guess  →"), onClick = onReady, accent = Mint)
}

@Composable
internal fun FeedbackContent(
    mode: GameMode, quest: Quest, round: Int, score: Int,
    firstChoice: Int, guess: Int, onBack: () -> Unit, onContinue: () -> Unit
) {
    val correct = if (mode == GameMode.DAILY) guess == quest.answer else firstChoice == guess
    var revealed by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { revealed = true }
    val appear by animateFloatAsState(if (revealed) 1f else .78f, tween(330), label = "earned point")
    BackLink(onBack)
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(Modifier.size(65.dp).scale(appear), contentAlignment = Alignment.Center) {
            Text(if (correct) "✦" else "◇", color = if (correct) Mint else Amber, fontSize = 58.sp)
        }
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Tag(if (correct) t("POINT EARNED") else t("KEEP GOING"), if (correct) Mint else Amber)
            Text("$score / 5 " + t("SO FAR"), color = Snow, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
    LargeTitle(
        title = if (correct && mode == GameMode.DAILY) t("Nailed it!")
            else if (correct) t("Perfect match!")
            else if (mode == GameMode.DAILY) t("You'll get the next.")
            else t("A new surprise!"),
        subtitle = if (mode == GameMode.DAILY) t("Here's what the right answer was.")
            else t("You just learned something new about each other.")
    )
    GlassCard {
        Text(quest.prompt, color = Soft, fontSize = 13.sp, lineHeight = 19.sp)
        if (mode == GameMode.DUO || mode == GameMode.REPLY) {
            Text(if (mode == GameMode.REPLY) t("YOUR FRIEND PICKED") else t("PLAYER 1 PICKED"),
                color = Violet, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = .7.sp)
            Text(quest.options[firstChoice], color = Snow, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(if (mode == GameMode.REPLY) t("YOU GUESSED") else t("PLAYER 2 GUESSED"),
                color = Violet, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = .7.sp)
            Text(quest.options[guess], color = if (correct) Mint else Amber, fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
        } else {
            Text(t("CORRECT ANSWER"), color = Violet, fontWeight = FontWeight.Bold,
                fontSize = 10.sp, letterSpacing = .7.sp)
            Text(quest.options[quest.answer], color = Mint, fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold)
        }
    }
    ProgressBar(round)
    SolidButton(if (round == QuestRules.ROUND_COUNT - 1) t("See my results  →") else t("Next question  →"),
        onClick = onContinue, accent = Mint)
}

@Composable
internal fun ResultContent(
    mode: GameMode, score: Int,
    onHome: () -> Unit, onPlayAgain: () -> Unit, onShare: () -> Unit
) {
    BrandHeader()
    Tag(t("QUEST COMPLETED"), Mint)
    ScoreOrb(score)
    Text(
        when (score) {
            5 -> t("You two are legends.")
            4 -> t("Almost unstoppable.")
            3 -> t("A lovely little win.")
            else -> t("The best part is playing.")
        },
        color = Snow, fontSize = (if (compactScreen()) 24 else 28).sp, fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
    )
    Text(
        if (mode == GameMode.DAILY) t("A fresh five-question drop is waiting tomorrow.")
        else t("Every answer makes a better story."),
        color = Soft, fontSize = 13.sp, lineHeight = 19.sp,
        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
    )
    GlassCard {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(t("THE LITTLE VICTORY"), color = Violet, fontSize = 10.sp,
                    letterSpacing = 1.sp, fontWeight = FontWeight.Bold)
                Text(if (score == 5) t("A perfect run!") else "$score " + t("RIGHT OUT OF FIVE"),
                    color = Snow, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            }
            SparkMark()
        }
    }
    SolidButton(t("Share your score  ↗"), onClick = onShare, accent = Mint)
    OutlineButton(
        if (mode == GameMode.DAILY) t("Replay daily drop")
        else if (mode == GameMode.REPLY) t("Back to more quests")
        else t("Another duo quest"), onClick = onPlayAgain
    )
    OutlineButton(t("Back to home"), onClick = onHome)
}

@Composable
internal fun InviteResultContent(onHome: () -> Unit, onShare: () -> Unit) {
    BrandHeader()
    Tag(t("CHALLENGE READY"), Mint)
    Spacer(Modifier.height(4.dp))
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        SparkMark()
    }
    LargeTitle(t("Now it's their turn."), t("Your five secret choices are ready to send."))
    GlassCard {
        Text(t("HOW IT WORKS"), color = Violet, fontWeight = FontWeight.ExtraBold,
            fontSize = 11.sp, letterSpacing = 1.sp)
        Text(t("HOW_TO_PLAY_STEPS"),
            color = Snow, fontSize = 14.sp, lineHeight = 25.sp)
        Tag(t("NO SIGN-UP NECESSARY"), Mint)
    }
    SolidButton(t("Invite a friend  ↗"), onClick = onShare, accent = Mint)
    OutlineButton(t("Back to home"), onClick = onHome)
}
