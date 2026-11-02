package com.parsfilo.duoquest.ui

import org.junit.Assert.*
import org.junit.Test

class QuestTextTest {
    @Test fun translatedHeroAndInstructionsRetainMultilineFormatting() {
        assertEquals(
            "Bugünü güzel\nbir anıya dönüştür.",
            QuestText.translate("Make today a\ngood story.", "tr")
        )
        assertEquals(
            "Küçük adımlar.\nBüyük eğlence.",
            QuestText.translate("A little progress.\nA lot of fun.", "tr")
        )
        assertTrue(QuestText.translate("HOW_TO_PLAY_STEPS", "tr").contains("\n02"))
    }

    @Test fun unknownLanguagesKeepEnglishAndSupportedLabelsTranslate() {
        assertEquals("The Daily Drop", QuestText.translate("The Daily Drop", "en"))
        assertEquals("The Daily Drop", QuestText.translate("The Daily Drop", "de"))
        assertEquals("Günün Beşlisi", QuestText.translate("The Daily Drop", "tr"))
        assertEquals("Arkadaşını davet et", QuestText.translate("Invite a friend to play", "tr"))
    }
}
