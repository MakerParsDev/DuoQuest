package com.makerpars.duoquest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Rule
import org.junit.Test

class DuoQuestFlowTest {
    @get:Rule val rule = createAndroidComposeRule<MainActivity>()

    private fun openDaily() {
        val first = rule.onAllNodesWithText("Play today's drop  →")
        if (first.fetchSemanticsNodes().isNotEmpty()) first.onFirst().performClick()
        else rule.onNodeWithText("Replay today's drop  →").performClick()
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
        rule.onNodeWithText("Invite a friend to play").performScrollTo().performClick()
        rule.onNodeWithText("Your secret picks.").assertExists()
        repeat(5) {
            rule.onNodeWithText("A").performScrollTo().performClick()
        }
        rule.onNodeWithText("CHALLENGE READY").assertExists()
        rule.onNodeWithText("Invite a friend  ↗").assertExists()
    }
}
