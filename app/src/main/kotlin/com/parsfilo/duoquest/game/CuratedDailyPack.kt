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
,
        item("q056", "Which planet is closest to the Sun?", "Güneş'e en yakın gezegen hangisidir?", listOf("Venus", "Mercury", "Earth", "Mars"), listOf("Venüs", "Merkür", "Dünya", "Mars"), 1, "science", "easy", "https://science.nasa.gov/mercury/facts/"),
        item("q057", "Which planet is the smallest in our solar system?", "Güneş sistemimizdeki en küçük gezegen hangisidir?", listOf("Mars", "Mercury", "Venus", "Neptune"), listOf("Mars", "Merkür", "Venüs", "Neptün"), 1, "science", "easy", "https://science.nasa.gov/mercury/facts/"),
        item("q058", "How long does Mercury take to orbit the Sun once?", "Merkür'ün Güneş çevresinde bir turu yaklaşık ne kadar sürer?", listOf("88 Earth days", "176 Earth days", "225 Earth days", "365 Earth days"), listOf("88 Dünya günü", "176 Dünya günü", "225 Dünya günü", "365 Dünya günü"), 0, "science", "medium", "https://science.nasa.gov/mercury/facts/"),
        item("q059", "Which planet is hottest because of its dense atmosphere?", "Yoğun atmosferi nedeniyle en sıcak gezegen hangisidir?", listOf("Mercury", "Mars", "Venus", "Jupiter"), listOf("Merkür", "Mars", "Venüs", "Jüpiter"), 2, "science", "medium", "https://science.nasa.gov/venus/venus-facts/"),
        item("q060", "Which planet rotates in the opposite direction to most planets?", "Çoğu gezegenin ters yönünde dönen gezegen hangisidir?", listOf("Venus", "Mars", "Jupiter", "Earth"), listOf("Venüs", "Mars", "Jüpiter", "Dünya"), 0, "science", "hard", "https://science.nasa.gov/venus/venus-facts/"),
        item("q061", "What is the largest moon in our solar system?", "Güneş sistemimizin en büyük uydusu hangisidir?", listOf("Earth's Moon", "Titan", "Ganymede", "Europa"), listOf("Dünya'nın Ay'ı", "Titan", "Ganymede", "Europa"), 2, "science", "medium", "https://science.nasa.gov/jupiter/jupiter-moons/ganymede/facts/"),
        item("q062", "Which moon is known to have its own magnetic field?", "Kendi manyetik alanına sahip olduğu bilinen uydu hangisidir?", listOf("Europa", "Ganymede", "Earth's Moon", "Phobos"), listOf("Europa", "Ganymede", "Dünya'nın Ay'ı", "Phobos"), 1, "science", "hard", "https://science.nasa.gov/jupiter/jupiter-moons/ganymede/facts/"),
        item("q063", "How long does one day on Saturn last?", "Satürn'de bir gün yaklaşık ne kadar sürer?", listOf("10.7 Earth hours", "24 Earth hours", "29.4 Earth days", "365 Earth days"), listOf("10,7 Dünya saati", "24 Dünya saati", "29,4 Dünya günü", "365 Dünya günü"), 0, "science", "medium", "https://science.nasa.gov/saturn/facts/"),
        item("q064", "What are Saturn's main rings mostly made of?", "Satürn'ün ana halkaları çoğunlukla nelerden oluşur?", listOf("Liquid water", "Ice and rock particles", "Hot gas", "Pure metal"), listOf("Sıvı sudan", "Buz ve kaya parçacıklarından", "Sıcak gazdan", "Saf metalden"), 1, "science", "medium", "https://science.nasa.gov/saturn/facts/"),
        item("q065", "Which spacecraft first discovered Jupiter's faint rings in 1979?", "Jüpiter'in sönük halkalarını 1979'da ilk hangi uzay aracı keşfetti?", listOf("Voyager 1", "Apollo 11", "Cassini", "Mariner 10"), listOf("Voyager 1", "Apollo 11", "Cassini", "Mariner 10"), 0, "science", "hard", "https://science.nasa.gov/jupiter/jupiter-facts/"),
        item("q066", "About what percentage of Earth's water is in the oceans?", "Dünya'daki suyun yaklaşık yüzde kaçı okyanuslardadır?", listOf("50 percent", "71 percent", "85 percent", "97 percent"), listOf("Yüzde 50", "Yüzde 71", "Yüzde 85", "Yüzde 97"), 3, "geography", "medium", "https://oceanservice.noaa.gov/facts/oceanwater.html"),
        item("q067", "Why do we see nearly the same side of the Moon from Earth?", "Dünya'dan Ay'ın neden neredeyse hep aynı yüzünü görürüz?", listOf("The Moon does not rotate", "It rotates once per orbit", "Earth blocks the far side", "The Sun lights only one side"), listOf("Ay hiç dönmediği için", "Her yörüngesinde bir kez döndüğü için", "Dünya uzak yüzünü kapattığı için", "Güneş yalnızca bir yüzünü aydınlattığı için"), 1, "science", "medium", "https://science.nasa.gov/moon/moon-phases/"),
        item("q068", "In which year did a spacecraft first image the Moon's far side?", "Bir uzay aracı Ay'ın uzak yüzünü ilk kez hangi yıl görüntüledi?", listOf("1945", "1959", "1969", "1972"), listOf("1945", "1959", "1969", "1972"), 1, "history", "hard", "https://science.nasa.gov/moon/facts/"),
        item("q069", "Which birds can fly backward?", "Hangi kuşlar geriye doğru uçabilir?", listOf("Eagles", "Hummingbirds", "Penguins", "Ostriches"), listOf("Kartallar", "Sinek kuşları", "Penguenler", "Deve kuşları"), 1, "nature", "easy", "https://nationalzoo.si.edu/migratory-birds/hummingbirds"),
        item("q070", "Which mammals are capable of true powered flight?", "Aktif kanat çırparak gerçek uçuş yapabilen memeliler hangileridir?", listOf("Flying squirrels", "Bats", "Sugar gliders", "Lemurs"), listOf("Uçan sincaplar", "Yarasalar", "Şeker planörleri", "Lemurlar"), 1, "nature", "medium", "https://www.si.edu/spotlight/bats/batfacts")
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
