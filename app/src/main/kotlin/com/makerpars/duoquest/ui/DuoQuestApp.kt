package com.makerpars.duoquest.ui

import android.content.Context
import androidx.core.content.edit
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.makerpars.duoquest.game.*
import java.time.LocalDate

private enum class Page { HOME, GAME, HANDOFF, FEEDBACK, RESULT, STATS }
private enum class Step { FIRST, GUESS }

@Composable
fun DuoQuestApp(incomingInvite: ChallengeInvite?, consumeInvite: () -> Unit, onShare: (String) -> Unit) {
    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences("duoquest_stats_v1", Context.MODE_PRIVATE) }
    val today = LocalDate.now()
    val haptics = LocalHapticFeedback.current
    var games by remember { mutableIntStateOf(preferences.getInt("games", 0)) }
    var best by remember { mutableIntStateOf(preferences.getInt("best", 0)) }
    var streak by remember { mutableIntStateOf(preferences.getInt("streak", 0)) }
    var matches by remember { mutableIntStateOf(preferences.getInt("matches", 0)) }
    var lastDaily by remember { mutableStateOf(preferences.getString("lastDaily", "") ?: "") }
    var page by rememberSaveable { mutableStateOf(Page.HOME) }
    var mode by rememberSaveable { mutableStateOf(GameMode.DAILY) }
    var seed by rememberSaveable { mutableIntStateOf(2026) }
    var round by rememberSaveable { mutableIntStateOf(0) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var firstChoice by rememberSaveable { mutableIntStateOf(-1) }
    var guess by rememberSaveable { mutableIntStateOf(-1) }
    var step by rememberSaveable { mutableStateOf(Step.FIRST) }
    var inviteAnswers by rememberSaveable { mutableStateOf("") }
    var replyAnswers by rememberSaveable { mutableStateOf("") }

    fun start(next: GameMode, challengeSeed: Int = (System.currentTimeMillis() % 100000).toInt(), answers: String = "") {
        mode = next
        seed = challengeSeed
        round = 0
        score = 0
        firstChoice = -1
        guess = -1
        step = Step.FIRST
        inviteAnswers = ""
        replyAnswers = answers
        page = Page.GAME
    }
    fun finish() {
        if (mode != GameMode.INVITE) games += 1
        if (mode == GameMode.DAILY) {
            best = maxOf(best, score)
            if (lastDaily != today.toString()) {
                val prev = runCatching { LocalDate.parse(lastDaily) }.getOrNull()
                streak = QuestRules.nextStreak(prev, today, streak)
                lastDaily = today.toString()
            }
        } else if (mode != GameMode.INVITE) {
            matches += score
        }
        preferences.edit {
            putInt("games", games)
            putInt("best", best)
            putInt("streak", streak)
            putInt("matches", matches)
            putString("lastDaily", lastDaily)
        }
        page = Page.RESULT
    }
    fun nextRound() {
        if (round == QuestRules.ROUND_COUNT - 1) finish()
        else {
            round += 1
            firstChoice = -1
            guess = -1
            step = Step.FIRST
            page = Page.GAME
        }
    }

    LaunchedEffect(incomingInvite) {
        if (incomingInvite != null) {
            start(GameMode.REPLY, incomingInvite.seed, incomingInvite.answers.joinToString(""))
            consumeInvite()
        }
    }
    BackHandler(page != Page.HOME) { page = Page.HOME }
    val quests = if (mode == GameMode.DAILY) QuestRules.dailyDeck(today) else QuestRules.duoDeck(seed)
    val current = quests[round]

    AnimatedContent(
        targetState = Triple(page, round, step),
        transitionSpec = {
            (fadeIn(tween(200)) + slideInVertically(tween(220)) { it / 18 })
                .togetherWith(fadeOut(tween(110)))
        },
        label = "quest page"
    ) { visible ->
      key(visible) {
        QuestBackdrop {
          when (visible.first) {
            Page.HOME -> HomeContent(
                games = games, streak = streak, best = best, playedToday = lastDaily == today.toString(),
                onDaily = { start(GameMode.DAILY) },
                onDuo = { start(GameMode.DUO) },
                onStats = { page = Page.STATS },
                onInvite = { start(GameMode.INVITE) }
            )
            Page.STATS -> StatsContent(games, best, streak, matches, onBack = { page = Page.HOME })
            Page.HANDOFF -> HandoffContent(round, onReady = { step = Step.GUESS; page = Page.GAME }, onBack = { page = Page.HOME })
            Page.GAME -> GameContent(mode, current, round, step == Step.FIRST,
                onBack = { page = Page.HOME },
                onAnswer = { index ->
                    haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    if (mode == GameMode.INVITE) {
                        inviteAnswers += index.toString()
                        nextRound()
                    } else if (mode == GameMode.DAILY) {
                        guess = index
                        score += QuestRules.dailyPoint(index, current)
                        page = Page.FEEDBACK
                    } else if (mode == GameMode.REPLY) {
                        firstChoice = replyAnswers[round].digitToInt()
                        guess = index
                        score += QuestRules.duoPoint(firstChoice, guess)
                        page = Page.FEEDBACK
                    } else if (step == Step.FIRST) {
                        firstChoice = index
                        page = Page.HANDOFF
                    } else {
                        guess = index
                        score += QuestRules.duoPoint(firstChoice, guess)
                        page = Page.FEEDBACK
                    }
                })
            Page.FEEDBACK -> FeedbackContent(mode, current, round, score, firstChoice, guess,
                onBack = { page = Page.HOME }, onContinue = { nextRound() })
            Page.RESULT -> if (mode == GameMode.INVITE) {
                InviteResultContent(
                    onHome = { page = Page.HOME },
                    onShare = {
                        onShare("I made a DuoQuest challenge for you! Try to guess my five secret answers.\n" +
                            ChallengeInviteCodec.encode(seed, inviteAnswers.map { it.digitToInt() }))
                    }
                )
            } else ResultContent(mode, score, onHome = { page = Page.HOME },
                onPlayAgain = { if (mode == GameMode.REPLY) page = Page.HOME else start(mode) },
                onShare = {
                    val modeName = if (mode == GameMode.DAILY) "Daily Drop" else "Know Me"
                    onShare("I scored $score/5 on DuoQuest $modeName! Can you beat me? ✦")
                })
          }
        }
      }
    }
}
