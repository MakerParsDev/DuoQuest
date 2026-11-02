package com.parsfilo.duoquest.game

import java.time.ZoneOffset
import java.time.LocalDate

/**
 * Source-backed additions for the 1.1 daily pack. Existing q01-q40 stay
 * unchanged; IDs q041+ are never reused. Editorial approval is explicit.
 */
data class CuratedDailyQuestion(
    val english: Quest,
    val turkishPrompt: String,
    val turkishOptions: List<String>,
    val category: String,
    val difficulty: String,
    val sourceUrl: String,
    val verifiedAt: LocalDate,
    val approved: Boolean
) {
    fun localized(language: String): Quest = if (language == "tr") {
        english.copy(prompt = turkishPrompt, options = turkishOptions)
    } else english
}

object CuratedDailyPack {
    private val verifiedOn = LocalDate.of(2026, 11, 2)
    val questions: List<CuratedDailyQuestion> = listOf(
        CuratedDailyQuestion(
            english = Quest("q041", "How many planets are in our solar system?", listOf("Seven", "Eight", "Nine", "Ten"), 1),
            turkishPrompt = "Güneş sistemimizde kaç gezegen vardır?",
            turkishOptions = listOf("Yedi", "Sekiz", "Dokuz", "On"),
            category = "science", difficulty = "easy",
            sourceUrl = "https://science.nasa.gov/solar-system/solar-system-facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q042", "About how old is our solar system?", listOf("1.2 billion years", "4.6 billion years", "8 billion years", "13.8 billion years"), 1),
            turkishPrompt = "Güneş sistemimiz yaklaşık kaç yaşındadır?",
            turkishOptions = listOf("1,2 milyar yıl", "4,6 milyar yıl", "8 milyar yıl", "13,8 milyar yıl"),
            category = "science", difficulty = "medium",
            sourceUrl = "https://science.nasa.gov/solar-system/solar-system-facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q043", "Which planet appears to spin on its side?", listOf("Mars", "Uranus", "Jupiter", "Mercury"), 1),
            turkishPrompt = "Hangi gezegen yan yatmış gibi döner?",
            turkishOptions = listOf("Mars", "Uranüs", "Jüpiter", "Merkür"),
            category = "science", difficulty = "easy",
            sourceUrl = "https://science.nasa.gov/uranus/facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q044", "Which planet is seventh from the Sun?", listOf("Saturn", "Neptune", "Uranus", "Jupiter"), 2),
            turkishPrompt = "Güneş'ten itibaren yedinci gezegen hangisidir?",
            turkishOptions = listOf("Satürn", "Neptün", "Uranüs", "Jüpiter"),
            category = "science", difficulty = "easy",
            sourceUrl = "https://science.nasa.gov/uranus/facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q045", "Which planet has a rotation period longer than its year?", listOf("Earth", "Venus", "Mars", "Jupiter"), 1),
            turkishPrompt = "Hangi gezegenin kendi eksenindeki dönüşü, Güneş çevresindeki bir turundan daha uzundur?",
            turkishOptions = listOf("Dünya", "Venüs", "Mars", "Jüpiter"),
            category = "science", difficulty = "medium",
            sourceUrl = "https://science.nasa.gov/venus/venus-facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q046", "Which planet has the largest volcano in the solar system?", listOf("Earth", "Venus", "Mars", "Io"), 2),
            turkishPrompt = "Güneş sistemindeki en büyük yanardağ hangi gezegendedir?",
            turkishOptions = listOf("Dünya", "Venüs", "Mars", "Io"),
            category = "science", difficulty = "medium",
            sourceUrl = "https://science.nasa.gov/mars/facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q047", "What is the name of the giant canyon system on Mars?", listOf("Olympus Mons", "Valles Marineris", "Hellas Planitia", "Elysium Planitia"), 1),
            turkishPrompt = "Mars'taki dev kanyon sisteminin adı nedir?",
            turkishOptions = listOf("Olympus Mons", "Valles Marineris", "Hellas Planitia", "Elysium Planitia"),
            category = "science", difficulty = "medium",
            sourceUrl = "https://science.nasa.gov/mars/facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q048", "Which planet has the Great Red Spot, a giant storm?", listOf("Saturn", "Jupiter", "Neptune", "Mars"), 1),
            turkishPrompt = "Dev bir fırtına olan Büyük Kırmızı Leke hangi gezegendedir?",
            turkishOptions = listOf("Satürn", "Jüpiter", "Neptün", "Mars"),
            category = "science", difficulty = "easy",
            sourceUrl = "https://science.nasa.gov/jupiter/jupiter-facts/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q049", "Which ocean is the largest on Earth?", listOf("Atlantic", "Indian", "Pacific", "Arctic"), 2),
            turkishPrompt = "Dünya'nın en büyük okyanusu hangisidir?",
            turkishOptions = listOf("Atlas", "Hint", "Pasifik", "Arktik"),
            category = "geography", difficulty = "easy",
            sourceUrl = "https://oceanexplorer.noaa.gov/ocean-fact/pacific-size/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q050", "The Challenger Deep is part of which feature?", listOf("Tonga Trench", "Mariana Trench", "Puerto Rico Trench", "Java Trench"), 1),
            turkishPrompt = "Challenger Deep hangi coğrafi oluşumun parçasıdır?",
            turkishOptions = listOf("Tonga Çukuru", "Mariana Çukuru", "Porto Riko Çukuru", "Java Çukuru"),
            category = "geography", difficulty = "medium",
            sourceUrl = "https://oceanexplorer.noaa.gov/ocean-fact/ocean-depth/", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q051", "What is the longest mountain range on Earth?", listOf("Andes", "Himalayas", "Mid-ocean ridge system", "Rocky Mountains"), 2),
            turkishPrompt = "Dünya'nın en uzun sıradağ sistemi hangisidir?",
            turkishOptions = listOf("And Dağları", "Himalayalar", "Okyanus ortası sırt sistemi", "Kayalık Dağlar"),
            category = "geography", difficulty = "hard",
            sourceUrl = "https://www.noaa.gov/education/resource-collections/ocean-coasts/ocean-floor-features", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q052", "Which ocean zone is also called the midnight zone?", listOf("Epipelagic", "Mesopelagic", "Bathypelagic", "Sunlight zone"), 2),
            turkishPrompt = "Okyanusun hangi bölgesi 'gece yarısı bölgesi' olarak da adlandırılır?",
            turkishOptions = listOf("Epipelajik", "Mezopelajik", "Batipelajik", "Güneş ışığı bölgesi"),
            category = "science", difficulty = "hard",
            sourceUrl = "https://www.noaa.gov/jetstream/ocean/layers-of-ocean", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q053", "Where do many eastern North American monarch butterflies spend the winter?", listOf("Northern Canada", "Central Mexico", "Iceland", "Greenland"), 1),
            turkishPrompt = "Kuzey Amerika'nın doğusundaki hükümdar kelebeklerinin çoğu kışı nerede geçirir?",
            turkishOptions = listOf("Kuzey Kanada", "Orta Meksika", "İzlanda", "Grönland"),
            category = "nature", difficulty = "medium",
            sourceUrl = "https://www.si.edu/spotlight/buginfo/monarch", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q054", "In which year did the U.S. Declaration of Independence get adopted?", listOf("1492", "1776", "1787", "1812"), 1),
            turkishPrompt = "ABD Bağımsızlık Bildirgesi hangi yıl kabul edildi?",
            turkishOptions = listOf("1492", "1776", "1787", "1812"),
            category = "history", difficulty = "easy",
            sourceUrl = "https://guides.loc.gov/declaration-of-independence/introduction", verifiedAt = verifiedOn, approved = true
        ),
        CuratedDailyQuestion(
            english = Quest("q055", "In which year was the U.S. Constitution signed?", listOf("1776", "1783", "1787", "1791"), 2),
            turkishPrompt = "ABD Anayasası hangi yıl imzalandı?",
            turkishOptions = listOf("1776", "1783", "1787", "1791"),
            category = "history", difficulty = "medium",
            sourceUrl = "https://guides.loc.gov/constitution", verifiedAt = verifiedOn, approved = true
        )
    )

    init { validate() }

    fun validate() {
        require(questions.map { it.english.id }.toSet().size == questions.size) { "Duplicate curated question ID" }
        questions.forEach { q ->
            require(q.english.id.startsWith("q") && q.english.answer in q.english.options.indices)
            require(q.turkishPrompt.isNotBlank() && q.turkishOptions.size == q.english.options.size)
            require(q.turkishOptions.all { it.isNotBlank() })
            require(q.category in setOf("science", "nature", "geography", "history", "culture", "technology", "sport", "language", "arts", "everyday", "general"))
            require(q.difficulty in setOf("easy", "medium", "hard"))
            require(q.sourceUrl.startsWith("https://") && q.verifiedAt <= LocalDate.now(ZoneOffset.UTC))
            require(q.approved)
        }
    }
}
