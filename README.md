# DuoQuest ✦

An offline-first Android social game built for quick, replayable moments.

## Play now

- **Daily Drop** — five deterministic trivia questions each day from a 40-question catalog. Your best daily score and consecutive-day streak are stored locally.
- **Know Me Better** — two players answer five prompts from a curated 35-prompt collection on the same device. The first player's choice remains hidden until the second player guesses.
- **Challenge sharing** — answer five Know Me Better prompts privately, then share a lightweight `duoquest://play?mode=reply&seed=…&answers=…&v=2` link. A friend with DuoQuest installed can guess your five answers on their device and share their result back. The challenge payload is intentionally **not secret or tamper-resistant**; no personal data is added. Some messaging apps do not hyperlink custom schemes; verified HTTPS app links require a future domain and landing page.
- **English / Türkçe** — switch languages from the home screen, including on Android 9. All 75 questions and answer options are translated; language preference stays on the device. The selected language does not change scoring or link interoperability.
- **Legacy invite links** — links without a version use the original 20-question social catalog (v1); newly shared links use the expanded 35-question catalog (v2). Both preserve the meaning and answer positions in either language.
- **Result sharing** — send a score using Android's system share sheet.
- **Your journey** — track completed games, personal best, streak and successful duo matches.

No login, network permission, cloud account, ads, billing or paid AI API is needed for the current version. This is an early playable foundation, not a launched store product.

## Verification

GitHub Actions runs unit tests, a debug APK build and Android Lint on pull requests and main pushes. Instrumented UI tests are run separately on an Android device.

## Build

Requirements: JDK 17+ and an Android SDK supporting the configured compile SDK.

```bash
./gradlew :app:testDebugUnitTest :app:assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`

The Android application ID is `com.parsfilo.duoquest`. Minimum Android API is 26.

## Configuration and secrets

Development and production credentials must be managed in Doppler, never committed to Git. The currently selected project/config for local development is `mobil-apps / duoquest`. Neither credentials nor Doppler exports belong in the repository. This offline-first version does not require any secret.

## Experience quality

The interface is optimized for compact Android screens and accessibility-sized touch targets,
with dark system bars, readable score tiles, reduced vertical waste in questions, gentle
page transitions, tactile option feedback, and animated result scoring. Motion follows the
system animator duration scale. All core game flows remain available offline.

## Status and follow-up

The first development milestone covers daily quizzes, local pass-and-play and offline cross-device invitation challenges with local Android verification. Planned work before public release: hands-on device UX acceptance, further accessibility acceptance and two-device invitation testing, more curated content, meaningful asynchronous invitations, retention instrumentation with appropriate consent, privacy disclosures, crash observability and monetization. The Google Play app has been registered under com.parsfilo.duoquest. Store listing copy is drafted and original bilingual store assets are prepared, but no AAB or production release has been submitted. Firebase Analytics and AdMob have now been configured for an unreleased development branch; production rollout remains deferred.

See [product direction](docs/PRODUCT.md).

## Privacy policy and store graphics

The English/Turkish privacy-policy source is at [docs/privacy-policy.html](docs/privacy-policy.html); an in-app, offline policy screen is available from the home screen. A **public HTTPS policy URL is not yet published or verified**.

Original Play icon, feature graphic and real Android device screenshots are in [store-assets/](store-assets/README.md). These are source assets, not evidence of a Play Console upload, approval or production publication.

The game requires no account or cloud-synchronized profile. Firebase Analytics is disabled by default and enabled only through the in-app privacy choice; AdMob uses Google UMP and restricted, non-personalized ads. Debug variants use official Google test ads. Play Console Ads/Advertising ID are set to Yes for the intended monetized release; Data Safety must still be updated from the exact built SDK configuration **before production**. The shared provider privacy site remains outside this repository.
