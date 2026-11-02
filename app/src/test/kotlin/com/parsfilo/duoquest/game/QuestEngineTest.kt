package com.parsfilo.duoquest.game
import java.time.LocalDate
import org.junit.Assert.*
import org.junit.Test

class QuestEngineTest {
    @Test fun everyQuestHasTurkishTextWithoutChangingAnswerIndices() {
        val all = QuestCatalog.duo + QuestCatalog.daily
        assertEquals(all.map { it.id }.toSet(), QuestTranslations.translatedIds)
        all.forEach { en ->
            val tr = QuestTranslations.localize(en, "tr")
            assertEquals(en.id, tr.id)
            assertEquals(en.answer, tr.answer)
            assertEquals(en.options.size, tr.options.size)
            assertTrue(tr.prompt.isNotBlank())
            assertTrue(tr.options.all { it.isNotBlank() })
            assertEquals(en, QuestTranslations.localize(en, "en"))
            assertEquals(en, QuestTranslations.localize(en, "de"))
        }
    }

    @Test fun bothInviteCatalogVersionsPreserveQuestionIdsAcrossLanguages() {
        for (seed in listOf(0, 17, 82017, 99999)) {
            for (v in listOf(1, 2)) {
                val english = QuestRules.duoDeck(seed, v)
                val turkish = english.map { QuestTranslations.localize(it, "tr") }
                assertEquals(english.map { it.id }, turkish.map { it.id })
                assertEquals(english.map { it.answer }, turkish.map { it.answer })
            }
        }
    }

    @Test fun contentIsUniqueAndEveryDeckIsPlayable() {
        assertEquals(190, QuestCatalog.daily.size)
        assertEquals(35, QuestCatalog.duo.size)
        assertEquals(190, QuestCatalog.daily.map { it.id }.toSet().size)
        assertEquals(35, QuestCatalog.duo.map { it.id }.toSet().size)
        assertTrue(QuestCatalog.daily.all { it.answer in it.options.indices })
        assertTrue(QuestCatalog.duo.all { it.answer == -1 })
        for (offset in 0..365) {
            val day = LocalDate.of(2026, 11, 2).plusDays(offset.toLong())
            assertEquals(5, QuestRules.dailyDeck(day).map { it.id }.toSet().size)
        }
        for (seed in -100..100) {
            assertEquals(5, QuestRules.duoDeck(seed).map { it.id }.toSet().size)
        }
    }

    @Test fun curatedDailyQuestionsHaveCompleteVerifiedBilingualMetadata() {
        assertEquals(150, CuratedDailyPack.questions.size)
        CuratedDailyPack.validate()
        CuratedDailyPack.questions.forEach { q ->
            assertTrue(q.approved)
            assertTrue(q.sourceUrl.startsWith("https://"))
            assertTrue(q.verifiedAt <= LocalDate.now(java.time.ZoneOffset.UTC))
            assertEquals(q.english.options.size, q.turkishOptions.size)
            assertTrue(q.english.answer in q.turkishOptions.indices)
            assertEquals(q.english.answer, QuestTranslations.localize(q.english, "tr").answer)
        }
    }

    @Test fun dailyIsDeterministicAndUnique() {
        val day = LocalDate.of(2026, 11, 2)
        val deck = QuestRules.dailyDeck(day)
        assertEquals(deck, QuestRules.dailyDeck(day))
        assertEquals(5, deck.map { it.id }.toSet().size)
        assertTrue(deck.all { it.answer in it.options.indices })
    }

    @Test fun dailyPackPartitionsQuestionsWithoutRepeatsWithinItsCycle() {
        val packDays = QuestCatalog.daily.size / QuestRules.ROUND_COUNT
        val firstDay = LocalDate.of(2026, 11, 2)
        val decks = (0 until packDays).map { QuestRules.dailyDeck(firstDay.plusDays(it.toLong())) }
        val allIds = QuestCatalog.daily.map { it.id }.toSet()
        val scheduledIds = decks.flatten().map { it.id }

        assertEquals(QuestCatalog.daily.size, scheduledIds.size)
        assertEquals(allIds, scheduledIds.toSet())
        assertEquals(scheduledIds.size, scheduledIds.toSet().size)
    }

    @Test fun dailyPackRepeatsOnlyAfterEveryQuestionHasBeenUsed() {
        val packDays = QuestCatalog.daily.size / QuestRules.ROUND_COUNT
        val firstDay = LocalDate.of(2026, 11, 2)

        assertEquals(
            QuestRules.dailyDeck(firstDay),
            QuestRules.dailyDeck(firstDay.plusDays(packDays.toLong()))
        )
        for (offset in 1 until packDays) {
            val current = QuestRules.dailyDeck(firstDay.plusDays(offset.toLong())).map { it.id }.toSet()
            val previous = QuestRules.dailyDeck(firstDay.plusDays((offset - 1).toLong())).map { it.id }.toSet()
            assertTrue(current.intersect(previous).isEmpty())
        }
    }

    @Test fun dailyScheduleIsIdenticalForEnglishAndTurkishClients() {
        val day = LocalDate.of(2026, 11, 2)
        val english = QuestRules.dailyDeck(day)
        val turkish = english.map { QuestTranslations.localize(it, "tr") }

        assertEquals(english.map { it.id }, turkish.map { it.id })
        assertEquals(english.map { it.answer }, turkish.map { it.answer })
    }

    @Test fun ninetyDayPackUsesAllFourHundredFiftyQuestionsBeforeRepeating() {
        val questions = (1..450).map { index ->
            Quest("daily-" + index.toString().padStart(3, '0'), "Question " + index, listOf("A", "B", "C", "D"), 0)
        }
        val firstDay = LocalDate.of(2026, 11, 2)
        val decks = (0 until 90).map { offset ->
            QuestRules.dailyDeck(firstDay.plusDays(offset.toLong()), questions, 0x44515501L)
        }
        val scheduledIds = decks.flatten().map { it.id }

        assertEquals(450, scheduledIds.size)
        assertEquals(450, scheduledIds.toSet().size)
        assertEquals(
            QuestRules.dailyDeck(firstDay, questions, 0x44515501L),
            QuestRules.dailyDeck(firstDay.plusDays(90), questions, 0x44515501L)
        )
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
