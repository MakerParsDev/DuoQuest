package com.parsfilo.duoquest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class DuoQuestFlowTest {
    @get:Rule val rule = createAndroidComposeRule<MainActivity>()

    private fun ensureEnglish() {
        val switch = rule.onAllNodesWithText("English  ↗")
        if (switch.fetchSemanticsNodes().isNotEmpty()) switch.onFirst().performClick()
    }

    private fun openDaily() {
        ensureEnglish()
        val first = rule.onAllNodesWithText("Play today's drop  →")
        if (first.fetchSemanticsNodes().isNotEmpty()) first.onFirst().performClick()
        else rule.onNodeWithText("Replay today's drop  →").performClick()
    }

    @Test fun privacyPolicyOpensWithoutExternalService() {
        ensureEnglish()
        rule.onNodeWithText("Privacy policy").performScrollTo().performClick()
        rule.onNodeWithText("Information stored on your device").assertExists()
        rule.onNodeWithText("Analytics, ads and permissions").performScrollTo().assertExists()
        rule.onNodeWithText("←  Back to home").performClick()
        rule.onNodeWithText("The Daily Drop").assertExists()
    }

    @Test fun analyticsPreferenceStartsOffAndCanBeRevoked() {
        ensureEnglish()
        rule.onNodeWithText("Privacy policy").performScrollTo().performClick()
        val consent = rule.onNodeWithContentDescription("Optional usage analytics")
        consent.performScrollTo().assertIsOff()
        consent.performClick()
        consent.assertIsOn()
        consent.performClick()
        consent.assertIsOff()
        rule.onNodeWithText("←  Back to home").performClick()
    }

    @Test fun installedPackageMatchesPlayConsoleApp() {
        assertEquals("com.parsfilo.duoquest", rule.activity.packageName)
    }

    @Test fun turkishLanguageChangesHomeAndQuestionWithoutResettingGameplay() {
        ensureEnglish()
        rule.onNodeWithText("Türkçe  ↗").performClick()
        rule.onNodeWithText("Günün Beşlisi").assertExists()
        rule.onNodeWithText("Bugünü güzel\nbir anıya dönüştür.").assertExists()
        val first = rule.onAllNodesWithText("Günün oyununa başla  →")
        if (first.fetchSemanticsNodes().isNotEmpty()) first.onFirst().performClick()
        else rule.onNodeWithText("Bugünün oyununu tekrar oyna  →").performClick()
        rule.onNodeWithText("Hızlı düşün.").assertExists()
        rule.onNodeWithText("SORU 1").assertExists()
        rule.onNodeWithText("D").assertIsDisplayed()
        // Home is still usable; next launch should retain language selection.
        rule.onNodeWithText("←  Ana sayfaya dön").performClick()
        rule.onNodeWithText("English  ↗").assertExists()
        rule.onNodeWithText("English  ↗").performClick()
        rule.onNodeWithText("The Daily Drop").assertExists()
    }

    @Test fun dailyEntryOpensQuestions() {
        openDaily()
        rule.onNodeWithText("Think fast.").assertExists()
        rule.onNodeWithText("QUESTION 1").assertExists()
        // Four options must fit on the small physical Android 9 display.
        rule.onNodeWithText("D").assertIsDisplayed()
    }

    @Test fun dailyGameReachesScoreScreen() {
        openDaily()
        repeat(5) { step ->
            rule.onNodeWithText("A").performScrollTo().performClick()
            rule.onNodeWithText(if (step == 4) "See my results  →" else "Next question  →")
                .performScrollTo().performClick()
        }
        rule.onNodeWithText("QUEST COMPLETED").assertExists()
        rule.onNodeWithText("Share your score  ↗").assertExists()
    }

    @Test fun inviteFlowCollectsFiveAnswersAndOffersSharing() {
        ensureEnglish()
        rule.onNodeWithText("Invite a friend to play").performScrollTo().performClick()
        rule.onNodeWithText("Your secret picks.").assertExists()
        repeat(5) {
            rule.onNodeWithText("A").performScrollTo().performClick()
        }
        rule.onNodeWithText("CHALLENGE READY").assertExists()
        rule.onNodeWithText("Invite a friend  ↗").assertExists()
    }
}
