package com.makerpars.duoquest.game

/** An intentionally non-secret, portable challenge. Never put private data into these links. */
data class ChallengeInvite(val seed: Int, val answers: List<Int>)

object ChallengeInviteCodec {
    fun encode(seed: Int, answers: List<Int>): String {
        require(seed in 0..99_999)
        require(answers.size == QuestRules.ROUND_COUNT && answers.all { it in 0..3 })
        return "duoquest://play?mode=reply&seed=" + seed + "&answers=" + answers.joinToString("")
    }

    fun decode(seed: String?, answers: String?): ChallengeInvite? {
        val parsedSeed = seed?.toIntOrNull()?.takeIf { it in 0..99_999 } ?: return null
        if (answers == null || answers.length != QuestRules.ROUND_COUNT ||
            answers.any { it !in '0'..'3' }
        ) return null
        return ChallengeInvite(parsedSeed, answers.map { it.digitToInt() })
    }
}
