package com.parsfilo.duoquest.game

import java.time.LocalDate
import java.time.ZoneOffset

/** Source-backed 1.1 additions; existing q01-q40 remain unchanged. */
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

    private fun item(
        id: String, en: String, tr: String, enOptions: List<String>, trOptions: List<String>,
        answer: Int, category: String, difficulty: String, sourceUrl: String
    ) = CuratedDailyQuestion(
        english = Quest(id, en, enOptions, answer), turkishPrompt = tr, turkishOptions = trOptions,
        category = category, difficulty = difficulty, sourceUrl = sourceUrl,
        verifiedAt = verifiedOn, approved = true
    )

    val questions: List<CuratedDailyQuestion> = listOf(
        item("q041", "How many planets are in our solar system?", "Güneş sistemimizde kaç gezegen vardır?", listOf("Seven", "Eight", "Nine", "Ten"), listOf("Yedi", "Sekiz", "Dokuz", "On"), 1, "science", "easy", "https://science.nasa.gov/solar-system/solar-system-facts/"),
        item("q042", "About how old is our solar system?", "Güneş sistemimiz yaklaşık kaç yaşındadır?", listOf("1.2 billion years", "4.6 billion years", "8 billion years", "13.8 billion years"), listOf("1,2 milyar yıl", "4,6 milyar yıl", "8 milyar yıl", "13,8 milyar yıl"), 1, "science", "medium", "https://science.nasa.gov/solar-system/solar-system-facts/"),
        item("q043", "Which planet appears to spin on its side?", "Hangi gezegen yan yatmış gibi döner?", listOf("Mars", "Uranus", "Jupiter", "Mercury"), listOf("Mars", "Uranüs", "Jüpiter", "Merkür"), 1, "science", "easy", "https://science.nasa.gov/uranus/facts/"),
        item("q044", "Which planet is seventh from the Sun?", "Güneş'ten itibaren yedinci gezegen hangisidir?", listOf("Saturn", "Neptune", "Uranus", "Jupiter"), listOf("Satürn", "Neptün", "Uranüs", "Jüpiter"), 2, "science", "easy", "https://science.nasa.gov/uranus/facts/"),
        item("q045", "Which planet has a rotation period longer than its year?", "Hangi gezegenin kendi eksenindeki dönüşü, Güneş çevresindeki bir turundan daha uzundur?", listOf("Earth", "Venus", "Mars", "Jupiter"), listOf("Dünya", "Venüs", "Mars", "Jüpiter"), 1, "science", "medium", "https://science.nasa.gov/venus/venus-facts/"),
        item("q046", "Which planet has the largest volcano in the solar system?", "Güneş sistemindeki en büyük yanardağ hangi gezegendedir?", listOf("Earth", "Venus", "Mars", "Io"), listOf("Dünya", "Venüs", "Mars", "Io"), 2, "science", "medium", "https://science.nasa.gov/mars/facts/"),
        item("q047", "What is the name of the giant canyon system on Mars?", "Mars'taki dev kanyon sisteminin adı nedir?", listOf("Olympus Mons", "Valles Marineris", "Hellas Planitia", "Elysium Planitia"), listOf("Olympus Mons", "Valles Marineris", "Hellas Planitia", "Elysium Planitia"), 1, "science", "medium", "https://science.nasa.gov/mars/facts/"),
        item("q048", "Which planet has the Great Red Spot, a giant storm?", "Dev bir fırtına olan Büyük Kırmızı Leke hangi gezegendedir?", listOf("Saturn", "Jupiter", "Neptune", "Mars"), listOf("Satürn", "Jüpiter", "Neptün", "Mars"), 1, "science", "easy", "https://science.nasa.gov/jupiter/jupiter-facts/"),
        item("q049", "Which ocean is the largest on Earth?", "Dünya'nın en büyük okyanusu hangisidir?", listOf("Atlantic", "Indian", "Pacific", "Arctic"), listOf("Atlas", "Hint", "Pasifik", "Arktik"), 2, "geography", "easy", "https://oceanexplorer.noaa.gov/ocean-fact/pacific-size/"),
        item("q050", "The Challenger Deep is part of which feature?", "Challenger Deep hangi coğrafi oluşumun parçasıdır?", listOf("Tonga Trench", "Mariana Trench", "Puerto Rico Trench", "Java Trench"), listOf("Tonga Çukuru", "Mariana Çukuru", "Porto Riko Çukuru", "Java Çukuru"), 1, "geography", "medium", "https://oceanexplorer.noaa.gov/ocean-fact/ocean-depth/"),
        item("q051", "What is the longest mountain range on Earth?", "Dünya'nın en uzun sıradağ sistemi hangisidir?", listOf("Andes", "Himalayas", "Mid-ocean ridge system", "Rocky Mountains"), listOf("And Dağları", "Himalayalar", "Okyanus ortası sırt sistemi", "Kayalık Dağlar"), 2, "geography", "hard", "https://www.noaa.gov/education/resource-collections/ocean-coasts/ocean-floor-features"),
        item("q052", "Which ocean zone is also called the midnight zone?", "Okyanusun hangi bölgesi 'gece yarısı bölgesi' olarak da adlandırılır?", listOf("Epipelagic", "Mesopelagic", "Bathypelagic", "Sunlight zone"), listOf("Epipelajik", "Mezopelajik", "Batipelajik", "Güneş ışığı bölgesi"), 2, "science", "hard", "https://www.noaa.gov/jetstream/ocean/layers-of-ocean"),
        item("q053", "Where do many eastern North American monarch butterflies spend the winter?", "Kuzey Amerika'nın doğusundaki hükümdar kelebeklerinin çoğu kışı nerede geçirir?", listOf("Northern Canada", "Central Mexico", "Iceland", "Greenland"), listOf("Kuzey Kanada", "Orta Meksika", "İzlanda", "Grönland"), 1, "nature", "medium", "https://www.si.edu/spotlight/buginfo/monarch"),
        item("q054", "In which year did the U.S. Declaration of Independence get adopted?", "ABD Bağımsızlık Bildirgesi hangi yıl kabul edildi?", listOf("1492", "1776", "1787", "1812"), listOf("1492", "1776", "1787", "1812"), 1, "history", "easy", "https://guides.loc.gov/declaration-of-independence/introduction"),
        item("q055", "In which year was the U.S. Constitution signed?", "ABD Anayasası hangi yıl imzalandı?", listOf("1776", "1783", "1787", "1791"), listOf("1776", "1783", "1787", "1791"), 2, "history", "medium", "https://guides.loc.gov/constitution")
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
