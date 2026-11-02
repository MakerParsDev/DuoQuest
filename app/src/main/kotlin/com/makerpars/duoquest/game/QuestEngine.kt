package com.makerpars.duoquest.game

import java.time.LocalDate
import kotlin.math.max

data class Quest(val id: String, val prompt: String, val options: List<String>, val answer: Int = -1) {
    init {
        require(options.size in 2..4)
        require(answer == -1 || answer in options.indices)
    }
}

enum class GameMode { DAILY, DUO, INVITE, REPLY }

object QuestRules {
    const val ROUND_COUNT = 5
    fun dailyDeck(date: LocalDate): List<Quest> {
        val start = (date.toEpochDay() % QuestCatalog.daily.size).toInt()
        return (0 until ROUND_COUNT).map { QuestCatalog.daily[(start + it * 7) % QuestCatalog.daily.size] }
    }
    fun duoDeck(seed: Int, version: Int = 2): List<Quest> {
        require(version in 1..2)
        // v1 preserves all links created before the social catalog was expanded.
        val pool = if (version == 1) QuestCatalog.duo.take(20) else QuestCatalog.duo
        val start = Math.floorMod(seed, pool.size)
        return (0 until ROUND_COUNT).map { pool[(start + it * 3) % pool.size] }
    }
    fun duoPoint(firstAnswer: Int, guess: Int) = if (firstAnswer == guess) 1 else 0
    fun dailyPoint(chosen: Int, quest: Quest) = if (chosen == quest.answer) 1 else 0
    fun nextStreak(lastPlayed: LocalDate?, today: LocalDate, priorStreak: Int): Int =
        when (lastPlayed) {
            today -> max(1, priorStreak)
            today.minusDays(1) -> max(0, priorStreak) + 1
            else -> 1
        }
}
