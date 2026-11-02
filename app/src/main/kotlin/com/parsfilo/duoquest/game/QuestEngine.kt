package com.parsfilo.duoquest.game

import java.time.LocalDate
import java.util.Collections
import java.util.Random
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

    // Keep this seed stable for a published content-pack revision. A new curated
    // pack can intentionally increment the revision and use a new seed.
    private const val DAILY_PACK_REVISION = 1
    private const val DAILY_PACK_SEED = 0x44515500L + DAILY_PACK_REVISION

    private fun orderDailyPool(questions: List<Quest>, seed: Long): List<Quest> {
        require(questions.size >= ROUND_COUNT && questions.size % ROUND_COUNT == 0) {
            "Daily question count must be a positive multiple of $ROUND_COUNT"
        }
        return questions.toMutableList().also { Collections.shuffle(it, Random(seed)) }
    }

    private val orderedDailyPool: List<Quest> by lazy {
        orderDailyPool(QuestCatalog.daily, DAILY_PACK_SEED)
    }

    /**
     * Returns the shared daily five-question set for a UTC calendar date.
     *
     * The catalog is shuffled once per pack revision, then partitioned into
     * disjoint five-question days. With the current 40 questions, a full
     * non-repeating cycle is 8 days; a 450-question pack extends it to 90 days.
     * Every client with the same pack revision and UTC date gets the same set.
     */
    fun dailyDeck(date: LocalDate): List<Quest> = dailyDeckFromOrderedPool(date, orderedDailyPool)

    internal fun dailyDeck(date: LocalDate, questions: List<Quest>, packSeed: Long): List<Quest> =
        dailyDeckFromOrderedPool(date, orderDailyPool(questions, packSeed))

    private fun dailyDeckFromOrderedPool(date: LocalDate, pool: List<Quest>): List<Quest> {
        val daysPerPack = pool.size / ROUND_COUNT
        val dayIndex = Math.floorMod(date.toEpochDay(), daysPerPack.toLong()).toInt()
        val first = dayIndex * ROUND_COUNT
        return pool.subList(first, first + ROUND_COUNT)
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
