package com.parsfilo.duoquest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
internal fun PrivacyContent(onBack: () -> Unit) {
    val tr = LocalDuoLanguage.current == "tr"
    BackLink(onBack)
    Tag(if (tr) "VERİLERİNİZ" else "YOUR DATA", Mint)
    LargeTitle(
        if (tr) "Gizlilik politikası" else "Privacy policy",
        if (tr) "2 Kasım 2026" else "November 2, 2026"
    )
    PrivacySection(
        if (tr) "Cihazınızda saklanan bilgiler" else "Information stored on your device",
        if (tr) "Oyun sayısı, en yüksek puan, günlük seri, eşleşen cevaplar ve dil seçimi yalnızca cihazınızda tutulur. Hesap veya bulut eşitlemesi gerekmez."
        else "Completed games, personal best, streak, matching answers and language preference stay on your device. No account or cloud sync is required."
    )
    PrivacySection(
        if (tr) "Arkadaş davetleri" else "Friend challenges",
        if (tr) "Davet paylaştığınızda seçtiğiniz beş cevap, oyun kodu ve bağlantı sürümü Android paylaşım menüsüyle gönderilir. Bağlantı gizli değildir. Alıcı ve paylaşım uygulamasını siz seçersiniz; ilgili hizmetlerin kendi gizlilik kuralları geçerlidir."
        else "When you share a challenge, its five selected answers, game seed and link version go through Android's share menu. The link is not secret. You choose the recipient and sharing app, whose privacy policy applies."
    )
    PrivacySection(
        if (tr) "Analiz, reklam ve izinler" else "Analytics, ads and permissions",
        if (tr) "Mevcut sürüm Firebase Analytics, AdMob, uzaktan takip veya kullanıcı hesabı içermez. Konum ve rehber erişimi istemez. Kişisel veri satmayız. Yeni hizmetler eklenirse bu açıklamayı ve mağaza beyanlarını güncelleyeceğiz."
        else "The current version has no Firebase Analytics, AdMob, remote tracking or user account. It does not request location or contact permissions. We do not sell personal data. We will update this policy and store disclosures before introducing new services."
    )
    PrivacySection(
        if (tr) "Bilgileri silme" else "Data deletion",
        if (tr) "Uygulama verilerini cihaz ayarlarından temizleyerek veya uygulamayı kaldırarak yerel bilgileri silebilirsiniz. Sunucuda saklanan bir kullanıcı profili yoktur."
        else "Clear DuoQuest's app storage or uninstall the app to erase locally saved game information. No server-hosted user profile exists."
    )
    PrivacySection(
        if (tr) "İletişim" else "Contact",
        "makerpars@gmail.com"
    )
}

@Composable
private fun PrivacySection(title: String, content: String) {
    GlassCard {
        Text(title, color = Mint, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text(content, color = Snow, fontSize = 13.sp, lineHeight = 20.sp)
    }
}
