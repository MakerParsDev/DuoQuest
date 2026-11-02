package com.makerpars.duoquest.game

import org.junit.Assert.*
import org.junit.Test

class ChallengeInviteTest {
    @Test fun roundTripAndQuestionOrderAreStable() {
        val answerList = listOf(0, 3, 1, 2, 0)
        val uri = ChallengeInviteCodec.encode(82017, answerList)
        assertEquals("duoquest://play?mode=reply&seed=82017&answers=03120", uri)
        assertEquals(ChallengeInvite(82017, answerList), ChallengeInviteCodec.decode("82017", "03120"))
        assertEquals(QuestRules.duoDeck(82017), QuestRules.duoDeck(82017))
    }
    @Test fun invalidAndTamperedLinksAreRejected() {
        assertNull(ChallengeInviteCodec.decode(null, "03120"))
        assertNull(ChallengeInviteCodec.decode("5", null))
        assertNull(ChallengeInviteCodec.decode("x", "03120"))
        assertNull(ChallengeInviteCodec.decode("-1", "03120"))
        assertNull(ChallengeInviteCodec.decode("9999999", "03120"))
        assertNull(ChallengeInviteCodec.decode("42", "0312"))
        assertNull(ChallengeInviteCodec.decode("42", "03125"))
        assertNull(ChallengeInviteCodec.decode("42", "03%20"))
    }
    @Test fun cannotEncodeOutOfBounds() {
        assertThrows(IllegalArgumentException::class.java) { ChallengeInviteCodec.encode(-1, listOf(0, 1, 2, 3, 0)) }
        assertThrows(IllegalArgumentException::class.java) { ChallengeInviteCodec.encode(23, listOf(0, 1, 2, 3, 4)) }
        assertThrows(IllegalArgumentException::class.java) { ChallengeInviteCodec.encode(23, listOf(0, 1)) }
    }
}
