package com.parsfilo.duoquest.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.parsfilo.duoquest.BuildConfig

private const val TEST_BANNER = "ca-app-pub-3940256099942544/9214589741"
private const val RELEASE_BANNER = "ca-app-pub-3312485084079132/5410659566"

/** A result-only ad; never interrupt questions, handoffs or gameplay. */
@Composable
internal fun ResultBanner(allowed: Boolean) {
    if (!allowed) return
    val context = LocalContext.current
    val ad = remember(context) {
        AdView(context).apply {
            adUnitId = if (BuildConfig.DEBUG) TEST_BANNER else RELEASE_BANNER
            setAdSize(AdSize.BANNER)
        }
    }
    LaunchedEffect(ad) { ad.loadAd(AdRequest.Builder().build()) }
    AndroidView(factory = { ad }, modifier = Modifier.fillMaxWidth())
    DisposableEffect(ad) { onDispose { ad.destroy() } }
}
