# DuoQuest Play release readiness — November 2, 2026

Play app com.parsfilo.duoquest is registered as a free game. The English and Turkish store descriptions are saved as drafts. The current app has no Firebase, AdMob, account, analytics backend or location/contacts permission.

Saved in Play Console, but not submitted: ads declaration "No", app access "No", and Data Safety draft declaring no app-collected/shared data for the current offline app and user-directed Android sharing. The privacy URL and target audience must be completed before Data Safety is final.

Prepared but not uploaded: icon 512x512, feature graphic 1024x500, actual Samsung screenshots in English and Turkish (see store-assets/), and bilingual HTML privacy policy at docs/privacy-policy.html. The privacy policy does not have a verified public HTTPS URL yet.

Suggested game category: Trivia (Soru Cevap). The category save did not complete; do not present as configured. Country availability, audience, content rating and remaining disclosures also need live Play checks.

Intended launch: direct 100% production release via GitHub Actions at a later separately authorized milestone; no voluntary alpha/beta/closed-test channel. Google may impose account-specific mandatory testing, review or release access gates; never bypass those. AAB signing, Play App Signing, Doppler-backed credentials and app rollout were not performed.

ASO: rank is not guaranteed. Use truthful metadata, real screenshots, stable game performance, audience-appropriate translations, and post-release real traffic experiments. Avoid keyword stuffing, fake ratings, false rankings or artificial installs.

Later Firebase/AdMob: register the exact Android package; decide on analytics/crash SDK and privacy requirements; integrate Ads with approved identifiers and test units, assess UMP consent (especially EEA/UK/Switzerland), publish and verify app-ads.txt on the approved developer domain, and update policy plus Google Play Ads/Data Safety declarations before a build containing those services goes live.
