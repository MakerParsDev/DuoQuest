# DuoQuest contributor guide

- This is a Kotlin + Jetpack Compose Android game. Keep the core gameplay deterministic and independently testable.
- Preserve the offline-first baseline: no silent backend dependency, analytics, advertising, paid API, or account requirement.
- Keep the application ID and deep-link scheme stable unless a migration is explicitly planned.
- Treat Android share content as user-visible; never include private values or secrets.
- Store credentials only in the team's designated external secret manager; do not commit .env, credential exports, keystores, signing passwords or local SDK paths.
- Prefer cohesive batches of changes with local unit tests and a debug build before proposing a PR.
- Author commits using the repository owner's verified personal Git identity. Do not add automated-agent co-author trailers.
- Never publish machine-specific paths, internal operating context or temporary debugging artifacts.
