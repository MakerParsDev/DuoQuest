# DuoQuest Play release readiness — November 2, 2026

Play app com.parsfilo.duoquest is registered as a free game. The English and Turkish store descriptions are saved as drafts. This branch integrates Firebase Analytics and AdMob without an account requirement or location/contacts permission. Analytics is opt-in and initially disabled. AdMob banner requests use UMP eligibility and Google general-rated, underage-of-consent restrictions. Debug builds use Google test ad IDs.

Saved in Play Console, but not submitted: Ads=YES, Advertising ID=YES (advertising, analytics, fraud prevention), App access=no restricted login. Earlier no-data Data Safety draft is **not suitable for the current SDK-integrated release**; review Google AdMob + Firebase SDK disclosures and submit accurate data types only after final AAB inspection. Target ages 13–15, 16–17 and 18+ are already saved; apply all age-aware advertising restrictions. Owner requires the unchanged common privacy URL https://parsfilo.com/privacy/.

Prepared but not uploaded: icon 512x512, feature graphic 1024x500, actual Samsung screenshots in English and Turkish (see store-assets/), and bilingual HTML privacy policy at docs/privacy-policy.html. A separate DuoQuest-specific privacy source is hosted on GitHub Pages, but the owner-chosen Play privacy URL is the unchanged common https://parsfilo.com/privacy/. Its coverage of DuoQuest remains an outstanding release-policy check.

Category Trivia (Soru Cevap), three truthful tags, age targeting and IARC rating were saved in Play Console. Store graphics still need upload; verify country availability and Data Safety before release.

Intended launch: direct 100% production release via GitHub Actions at a later separately authorized milestone; no voluntary alpha/beta/closed-test channel. Google may impose account-specific mandatory testing, review or release access gates; never bypass those. AAB signing, Play App Signing, Doppler-backed credentials and app rollout were not performed.

ASO: rank is not guaranteed. Use truthful metadata, real screenshots, stable game performance, audience-appropriate translations, and post-release real traffic experiments. Avoid keyword stuffing, fake ratings, false rankings or artificial installs.

New Google service registrations (2026-11-02): Firebase Spark project duoquest-34b10 (Android com.parsfilo.duoquest, Analytics enabled); AdMob DuoQuest app ca-app-pub-3312485084079132~9078441833 (awaiting app readiness review), result banner ca-app-pub-3312485084079132/5410659566. Before production verify device behavior, UMP privacy options and teen-safe advertising, app-ads.txt on the authorized developer domain, shared privacy coverage and accurate Play Data Safety. No actual Play AAB upload, production, or paid operation occurred.
