# DuoQuest — Product direction

## Positioning

A social game with high-quality, short-form moments. Easy to try, pleasant to share, rewarding to return to. Initial international audience; English and Turkish selectable inside the app, including on Android 9.

## Current experience

1. Launch without authentication.
2. Play the same five Daily Drop questions as other users on a given calendar date.
3. Play Know Me Better with a friend by passing the phone between questions.
4. Share a finished five-answer challenge using Android's share sheet. Another device can open it and guess your answers, with no backend or login. This is not secure or secret sharing; the encoded answers are plain text.
5. Return to improve your personal best and daily streak.

**Important:** Local stats are not a global leaderboard; the deep link exchanges a static, tamperable challenge, not live matchmaking or a server-recorded result. Some apps do not make custom-scheme links clickable. No purchases, online account or monetization exist yet.

## UI and game feel

- Cohesive midnight/violet/mint art direction and custom in-app drawn mark.
- Balanced stat cards, compact four-answer gameplay and readable system bars for older Android devices.
- Gentle navigation/selection/score animations and light system haptics; no disruptive autoplay or sound.
- Curated 40-question daily and 35-prompt social pools; no repetitive text generation service.
- English/Turkish UI and all quest prompts, with persistent in-app language choice. Question IDs, answer indices and user history are unchanged.
- Old v1 friend invites retain the original 20-prompt deck; new v2 invites use 35 prompts. Both function across language settings, without online verification or guaranteed chat-app linkification.
- Same offline gameplay and locally stored history.

## Prioritized product work

1. **Experience:** Test small and large screens and system UI, accessibility, back navigation, content readability, and actual two-person flow.
2. **Retention:** Expand and editorially check question content; validate both languages on devices; validate session completion and real-user return.
3. **Social loop:** Test the five-answer share/receive flow on two phones. To get clickable public links and shared score history, add verified HTTPS app links and a lean server-backed challenge exchange only once privacy, abuse and hosting requirements are clear.
4. **Metrics:** Add privacy-respecting analytics after consent/data inventory is complete. Analyze D1, D7, D30, invite rate, game completion and repeat rounds.
5. **Monetization:** When core play is validated, consider optional rewarded ads, light non-intrusive interstitials, and paid themed packs. Do not require rewarded ads to finish a normal game.
6. **Store release:** Only after product acceptance, complete Play Console, AdMob, app-ads.txt, Data safety, privacy policy, signing and production distribution.

## Success criteria for product experiments

Targets for early cohorts, **not measured results**:
- 50% of first sessions finish a quest.
- 20% of weekly active users share an invitation or score.
- 30% D1 / 12% D7 / 8% D30 retention (ambitious hypotheses to test).
- Positive unit economics after real country mix, fill rate, SDK fees and user acquisition costs are known.

No auto-created paid service, second deployment, or additional domain is required for the current milestone.
