package com.parsfilo.duoquest.game

/** Only presentation changes by language. Question IDs, answer indices and link seeds stay stable. */
object QuestTranslations {
    private data class Copy(val prompt: String, val options: List<String>)
    private fun copy(prompt: String, vararg options: String) = Copy(prompt, options.toList())

    private val turkish: Map<String, Copy> = mapOf(
        "d01" to copy("Birlikte hayalinizdeki hafta sonu?", "Yolculuğa çıkmak", "Film maratonu", "Lezzet turu", "Doğaya kaçmak"),
        "d02" to copy("Hangi süper gücü seçerdin?", "Zamanda yolculuk", "Işınlanmak", "Zihin okumak", "Görünmez olmak"),
        "d03" to copy("Batan bir uzay gemisinden neyi kurtarırsın?", "Atıştırmalıkları", "Telefonumu", "Robotu", "Acil durum kılavuzunu"),
        "d04" to copy("Sıradaki maceranı seç.", "Kuzey ışıkları", "Gizli bir plaj", "Dağ kulübesi", "Gece Tokyo"),
        "d05" to copy("Gece acıktığında ne yersin?", "Pizza", "Suşi", "Dondurma", "Acılı erişte"),
        "d06" to copy("Hayalindeki sürpriz hangisi?", "Konser bileti", "El yapımı hediye", "Gizli bir kaçamak", "Yatakta kahvaltı"),
        "d07" to copy("Yanında hangi hayvan olsun isterdin?", "Kurnaz tilki", "Minik ejderha", "Sadık köpek", "Yaramaz kedi"),
        "d08" to copy("Fazladan 1.000 doların olsa?", "Seyahate çıkarım", "Eşyalarımı yenilerim", "Arkadaşlarıma harcarım", "Biriktiririm"),
        "d09" to copy("Bir film evreni seç.", "Fantastik krallık", "Uzay macerası", "Süper kahraman şehri", "Gizemli ada"),
        "d10" to copy("Gizli yeteneğin hangisi?", "İyi yemek bulmak", "Ayrıntıları hatırlamak", "İnsanları güldürmek", "Bulmaca çözmek"),
        "d11" to copy("Partiye bir şey getireceksin. Ne olur?", "Müzik listesi", "Harika atıştırmalıklar", "Çılgın oyunlar", "Sohbet"),
        "d12" to copy("Düşünmeden en çok ne satın alırsın?", "Teknolojik oyuncak", "Kitap", "Ev dekoru", "Küçük bir ödül"),
        "d13" to copy("Yarın nerede uyanmak istersin?", "Deniz kenarı", "Büyükşehir", "Dağlar", "Sakin bir köy"),
        "d14" to copy("Hangi yarışmayı kazanırsın?", "Bilgi yarışması", "Yemek yarışması", "Kaçış odası", "Dans yarışması"),
        "d15" to copy("Yağmurlu bir günde ne yaparsın?", "Oyun oynarım", "Kitap okurum", "Yemek yaparım", "Yine dışarı çıkarım"),
        "d16" to copy("Hayalindeki aracı seç.", "Klasik araba", "Karavan", "Motosiklet", "Uzay gemisi"),
        "d17" to copy("Arkadaşların seni nasıl anlatır?", "Planlamacı", "Şakacı", "Maceracı", "İyi dinleyici"),
        "d18" to copy("Neye mutlaka zaman ayırırsın?", "Müzik", "Arkadaşlar", "Güzel yemek", "Huzur ve sessizlik"),
        "d19" to copy("En iyi takım adı hangisi?", "Kozmik Kaos", "Gece Kulübü", "Sürpriz Takım", "Şanslı Efsaneler"),
        "d20" to copy("Bir kez yaşayabileceğin anı seç.", "Uzaydan Dünya'yı görmek", "Yunuslarla yüzmek", "Kahramanımla tanışmak", "Zamanda yolculuk"),
        "d21" to copy("Elektrikler kesildi. Ne yaparsın?", "Mum yakarım", "Korku hikâyesi anlatırım", "Atıştırmalık bulurum", "Yıldızları izlerim"),
        "d22" to copy("Hayalindeki küçük dükkân?", "Plakçı", "Küçük kitapçı", "Kahve standı", "Bitki dükkânı"),
        "d23" to copy("Arkadaşın gece yarısı aradı. Sen...", "Hemen açarım", "Komik bir görsel yollarım", "Acil mi diye sorarım", "Yanına atıştırmalık götürürüm"),
        "d24" to copy("Hangi gizemi çözmek isterdin?", "Kayıp bir tablo", "Gizli bir tünel", "Hayalet gemi", "Kayıp şehir"),
        "d25" to copy("Yolculuk müziği neyle başlasın?", "90'ların klasikleri", "Bağımsız keşifler", "Dans şarkıları", "Film müzikleri"),
        "d26" to copy("Bir gecede hangi beceriyi öğrenirdin?", "Beş dil konuşmak", "Piyano çalmak", "Her şeyi pişirmek", "Portre çizmek"),
        "d27" to copy("Beklenmedik bir tatil günün var. İlk durak?", "Yeni bir kafe", "Yürüyüş parkuru", "İkinci el pazarı", "Yatağa geri dönmek"),
        "d28" to copy("Mükemmel doğum günü nasıl olur?", "Küçük bir yemek", "Büyük bir sürpriz", "Yeni bir deneyim", "Sakin bir kaçamak"),
        "d29" to copy("Zaman kapsülüne ne koyarsın?", "Eski bir fotoğraf", "El yazısı bir not", "Sevdiğim kulaklık", "Küçük bir hatıra"),
        "d30" to copy("Hayalindeki evde mutlaka ne olmalı?", "Çatı bahçesi", "Kocaman mutfak", "Okuma köşesi", "Oyun odası"),
        "d31" to copy("Takımının maskotunu seç.", "Uzay su samuru", "Kadife ejderha", "Disko pengueni", "Turbo kaplumbağa"),
        "d32" to copy("Küçük bir başarıyı nasıl kutlarsın?", "Mutluluk dansı", "En sevdiğim tatlı", "Arkadaşımı ararım", "Uzun bir yürüyüş"),
        "d33" to copy("Bir anı yeniden yaşayabilsen?", "Muhteşem bir konser", "Aile yemeği", "İlk büyük macera", "Mükemmel gün batımı"),
        "d34" to copy("Gizli kaçış rotan hangisi?", "Sahil kasabası", "Dağ evi", "Işıltılı şehir", "Çöl kampı"),
        "d35" to copy("Sürpriz bir kutu geldi. İçinden ne çıksın?", "El yapımı bir şey", "Komik bir alet", "Yeni bir kitap", "Seyahat bileti"),
        "q01" to copy("Halkalarıyla ünlü gezegen hangisi?", "Mars", "Satürn", "Venüs", "Merkür"),
        "q02" to copy("Altıgenin kaç kenarı vardır?", "Beş", "Altı", "Yedi", "Sekiz"),
        "q03" to copy("Hangi hayvan renk değiştirebilir?", "Koala", "Bukalemun", "Su samuru", "Penguen"),
        "q04" to copy("Japonya'nın başkenti neresidir?", "Seul", "Kyoto", "Tokyo", "Osaka"),
        "q05" to copy("En büyük okyanus hangisidir?", "Atlas", "Arktik", "Hint", "Pasifik"),
        "q06" to copy("Altının kimyasal simgesi nedir?", "Go", "Ag", "Au", "Gd"),
        "q07" to copy("Hangi müzik aletinin 88 tuşu vardır?", "Keman", "Piyano", "Flüt", "Gitar"),
        "q08" to copy("İki saatte kaç dakika vardır?", "90", "100", "120", "150"),
        "q09" to copy("Machu Picchu hangi ülkededir?", "Meksika", "Peru", "Şili", "Bolivya"),
        "q10" to copy("Bitkinin hangi bölümü su emer?", "Taç yaprak", "Yapraklar", "Kökler", "Meyve"),
        "q11" to copy("12 × 8 kaçtır?", "88", "96", "108", "112"),
        "q12" to copy("DNA'nın açılımı nedir?", "Deoksiribonükleik asit", "Dinamik doğal atom", "Dijital ağ arşivi", "Çift azot asidi"),
        "q13" to copy("Sahra Çölü hangi kıtadadır?", "Asya", "Afrika", "Avustralya", "Avrupa"),
        "q14" to copy("Karadaki en hızlı hayvan hangisi?", "Aslan", "At", "Çita", "Ceylan"),
        "q15" to copy("Bitkiler hangi gazı emer?", "Oksijen", "Karbondioksit", "Helyum", "Hidrojen"),
        "q16" to copy("Yıldızların oluşturduğu desene ne denir?", "Takımyıldız", "Galaksi", "Bulutsu", "Yörünge"),
        "q17" to copy("En küçük asal sayı hangisidir?", "0", "1", "2", "3"),
        "q18" to copy("Gökkuşağında genellikle kaç renk sayılır?", "Beş", "Altı", "Yedi", "Sekiz"),
        "q19" to copy("Kâğıt ilk nerede icat edilmiştir?", "Mısır", "Çin", "Yunanistan", "Hindistan"),
        "q20" to copy("İnsan vücudunun en büyük organı hangisi?", "Karaciğer", "Kalp", "Deri", "Akciğer"),
        "q21" to copy("Dünya'ya en yakın yıldız hangisi?", "Sirius", "Güneş", "Vega", "Proxima Centauri"),
        "q22" to copy("H2O hangi maddenin formülüdür?", "Tuz", "Hidrojen", "Su", "Oksijen"),
        "q23" to copy("Hangi şeklin üç kenarı vardır?", "Kare", "Üçgen", "Daire", "Beşgen"),
        "q24" to copy("Guakamolenin ana malzemesi nedir?", "Domates", "Avokado", "Salatalık", "Bezelye"),
        "q25" to copy("Hangi sporda tüylü top kullanılır?", "Tenis", "Squash", "Badminton", "Voleybol"),
        "q26" to copy("Dik açı kaç derecedir?", "45", "60", "90", "180"),
        "q27" to copy("Brezilya'nın resmî dili nedir?", "İspanyolca", "Portekizce", "Fransızca", "İtalyanca"),
        "q28" to copy("Güneş'e en yakın gezegen hangisi?", "Dünya", "Venüs", "Merkür", "Mars"),
        "q29" to copy("Ahtapotun kaç kalbi vardır?", "Bir", "İki", "Üç", "Dört"),
        "q30" to copy("Yıldızlı Gece tablosunu kim yaptı?", "Picasso", "Van Gogh", "Monet", "Matisse"),
        "q31" to copy("Afrika ile Avustralya arasında hangi okyanus var?", "Arktik", "Hint", "Atlas", "Güney"),
        "q32" to copy("Dünya atmosferinde en çok hangi gaz bulunur?", "Oksijen", "Azot", "Hidrojen", "Neon"),
        "q33" to copy("200'ün %15'i kaçtır?", "15", "20", "30", "40"),
        "q34" to copy("Futbolda bir takım sahada kaç oyuncuyla oynar?", "Dokuz", "On", "On bir", "On iki"),
        "q35" to copy("Hangisi memelidir?", "Köpek balığı", "Kurbağa", "Yunus", "Penguen"),
        "q36" to copy("Termometre ne ölçer?", "Rüzgâr", "Sıcaklık", "Mesafe", "Hız"),
        "q37" to copy("Majör dizide kaç farklı nota vardır?", "Beş", "Altı", "Yedi", "Sekiz"),
        "q38" to copy("Hangisi programlama dilidir?", "Python", "Falcon", "Lynx", "Otter"),
        "q39" to copy("Güneş sisteminin en büyük gezegeni hangisi?", "Neptün", "Satürn", "Jüpiter", "Uranüs"),
        "q40" to copy("Artık yılda kaç gün bulunur?", "364", "365", "366", "367")
    )

    val translatedIds: Set<String> get() = turkish.keys + CuratedDailyPack.questions.map { it.english.id }

    fun localize(quest: Quest, language: String): Quest {
        if (language != "tr") return quest
        CuratedDailyPack.questions.firstOrNull { it.english.id == quest.id }?.let {
            return it.localized(language)
        }
        val c = turkish[quest.id] ?: return quest
        check(c.options.size == quest.options.size) { "Localized options mismatch: " + quest.id }
        return quest.copy(prompt = c.prompt, options = c.options)
    }
}
