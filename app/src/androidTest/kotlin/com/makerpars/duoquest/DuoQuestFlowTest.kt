package com.makerpars.duoquest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Rule
import org.junit.Test

class DuoQuestFlowTest {
    @get:Rule val rule = createAndroidComposeRule<MainActivity>()

    @Test fun dailyEntryOpensQuestions() {
        rule.onNodeWithText("Play today's drop  →").assertExists().performClick()
        rule.onNodeWithText("Think fast.").assertExists()
        rule.onNodeWithText("QUESTION 1").assertExists()
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
