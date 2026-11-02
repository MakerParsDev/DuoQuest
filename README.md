# DuoQuest ✦

An offline-first Android social game built for quick, replayable moments.

## Play now

- **Daily Drop** — five deterministic trivia questions each day. Your best daily score and consecutive-day streak are stored locally.
- **Know Me Better** — two players answer five prompts on the same device. The first player's choice remains hidden until the second player guesses.
- **Challenge sharing** — answer five Know Me Better prompts privately, then share a lightweight `duoquest://play?mode=reply&seed=…&answers=…` link. A friend with DuoQuest installed can guess your five answers on their device and share their result back. The challenge payload is intentionally **not secret or tamper-resistant**; no personal data is added. Some messaging apps do not hyperlink custom schemes; verified HTTPS app links require a future domain and landing page.
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

The Android application ID is `com.makerpars.duoquest`. Minimum Android API is 26.

## Configuration and secrets

Development and production credentials must be managed in Doppler, never committed to Git. The currently selected project/config for local development is `mobil-apps / duoquest`. Neither credentials nor Doppler exports belong in the repository. This offline-first version does not require any secret.

## Status and follow-up

The first development milestone covers daily quizzes, local pass-and-play and offline cross-device invitation challenges with local Android verification. Planned work before public release: hands-on device UX acceptance, accessibility and localization, more curated content, meaningful asynchronous invitations, retention instrumentation with appropriate consent, privacy disclosures, crash observability and monetization. Google Play / AdMob console setup is intentionally deferred.

See [product direction](docs/PRODUCT.md).
