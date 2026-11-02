package com.parsfilo.duoquest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import com.parsfilo.duoquest.game.ChallengeInvite
import com.parsfilo.duoquest.game.ChallengeInviteCodec
import com.parsfilo.duoquest.ui.DuoQuestApp
import com.parsfilo.duoquest.ui.theme.DuoQuestTheme

class MainActivity : ComponentActivity() {
    private val incomingChallenge = mutableStateOf<ChallengeInvite?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Dark system bars remain legible on Android 8+ and in edge-to-edge mode.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        if (savedInstanceState == null) acceptChallenge(intent)
        setContent {
            DuoQuestTheme(darkTheme = true) {
                DuoQuestApp(
                    incomingInvite = incomingChallenge.value,
                    consumeInvite = { incomingChallenge.value = null },
                    onShare = { text ->
                        val share = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, text)
                        }
                        startActivity(Intent.createChooser(share, getString(R.string.share_quest)))
                    }
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        acceptChallenge(intent)
    }

    private fun acceptChallenge(intent: Intent?) {
        val uri = intent?.data ?: return
        if (uri.scheme == "duoquest" && uri.host == "play" &&
            uri.getQueryParameter("mode") == "reply"
        ) {
            incomingChallenge.value = ChallengeInviteCodec.decode(
                uri.getQueryParameter("seed"), uri.getQueryParameter("answers"),
                uri.getQueryParameter("v")
            )
        }
    }
}
