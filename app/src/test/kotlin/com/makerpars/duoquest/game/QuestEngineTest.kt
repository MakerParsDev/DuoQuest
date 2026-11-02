package com.makerpars.duoquest.game
import java.time.LocalDate
import org.junit.Assert.*
import org.junit.Test

class QuestEngineTest {
    @Test fun dailyIsDeterministicAndUnique() {
        val day = LocalDate.of(2026, 11, 2)
        val deck = QuestRules.dailyDeck(day)
        assertEquals(deck, QuestRules.dailyDeck(day))
        assertEquals(5, deck.map { it.id }.toSet().size)
        assertTrue(deck.all { it.answer in it.options.indices })
    }
    @Test fun duoIsDeterministic() {
        assertEquals(QuestRules.duoDeck(23891), QuestRules.duoDeck(23891))
        assertEquals(5, QuestRules.duoDeck(-42).map { it.id }.toSet().size)
    }
    @Test fun scoreAndStreak() {
        assertEquals(1, QuestRules.duoPoint(2, 2))
        assertEquals(0, QuestRules.duoPoint(2, 1))
        assertEquals(1, QuestRules.dailyPoint(1, QuestCatalog.daily.first()))
        val day = LocalDate.of(2026, 11, 2)
        assertEquals(4, QuestRules.nextStreak(day.minusDays(1), day, 3))
        assertEquals(3, QuestRules.nextStreak(day, day, 3))
        assertEquals(1, QuestRules.nextStreak(day.minusDays(3), day, 8))
    }
}
