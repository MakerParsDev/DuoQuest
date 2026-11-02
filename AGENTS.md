# DuoQuest contributor guide

- This is a Kotlin + Jetpack Compose Android game. Keep the core gameplay deterministic and independently testable.
- Preserve offline-first gameplay with no required account, cloud matchmaking or paid API. Firebase Analytics and AdMob are explicitly approved for a future monetized release; gate telemetry behind user opt-in and ads behind UMP consent, with Google demo IDs in debug.
- Keep the application ID and deep-link scheme stable unless a migration is explicitly planned.
- Treat Android share content as user-visible; never include private values or secrets.
- Store credentials only in the team's designated external secret manager; do not commit .env, credential exports, keystores, signing passwords or local SDK paths.
- Prefer cohesive batches of changes with local unit tests and a debug build before proposing a PR.
- Author commits using the repository owner's verified personal Git identity. Do not add automated-agent co-author trailers.
- Never publish machine-specific paths, internal operating context or temporary debugging artifacts.
