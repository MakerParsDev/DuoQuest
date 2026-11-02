package com.parsfilo.duoquest

import android.app.Activity
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AgeRestrictedTreatment
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A conservative all-ages configuration for a 13+ mixed audience. The app does
 * not ask for date of birth or infer age; until a verified age gate is designed,
 * all requests use under-age-of-consent restrictions and general-rated ads.
 *
 * Ads are never requested before UMP finishes its update and canRequestAds().
 */
internal class AdsConsent(private val activity: Activity, private val ready: (Boolean) -> Unit) {
    private val consent = UserMessagingPlatform.getConsentInformation(activity)
    private val started = AtomicBoolean(false)

    fun refresh() {
        val params = ConsentRequestParameters.Builder()
            .setTagForUnderAgeOfConsent(true)
            .build()
        consent.requestConsentInfoUpdate(
            activity,
            params,
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) {
                    beginIfPermitted()
                }
            },
            { beginIfPermitted() }
        )
    }

    private fun beginIfPermitted() {
        if (!consent.canRequestAds() || !started.compareAndSet(false, true)) return
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setAgeRestrictedTreatment(AgeRestrictedTreatment.CHILD)
                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
                .build()
        )
        Thread {
            MobileAds.initialize(activity) {
                activity.runOnUiThread { ready(consent.canRequestAds()) }
            }
        }.start()
    }

    val privacyOptionsRequired: Boolean
        get() = consent.privacyOptionsRequirementStatus ==
            ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED

    fun showPrivacyOptions() {
        if (privacyOptionsRequired) {
            UserMessagingPlatform.showPrivacyOptionsForm(activity) {
                // Respect the current UMP answer on the next ad request/session.
                ready(consent.canRequestAds())
            }
        }
    }
}
