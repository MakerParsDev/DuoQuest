# DuoQuest question-content standard

This document defines the editorial and compatibility contract for the 1.1 question expansion. It does not change the 1.0 release currently under Google Play review.

## Daily Drop pack

- A daily pack contains exactly five questions per UTC calendar day.
- Pack size must be a positive multiple of five. The first expanded milestone is 90 days / 450 daily questions.
- Every client with the same pack revision and UTC date must resolve to the same five question IDs and answer indices, regardless of locale or device timezone.
- Within one pack cycle, each question is scheduled exactly once. The pack repeats only after all its daily slots have been consumed.
- The pack has an explicit revision and deterministic shuffle seed. Never silently reorder or mutate a published pack revision. Content changes that alter the daily mapping require a new revision.
- The app remains offline-first. The pack ships inside the app; no login, backend, paid API or live question generation is required.

## Required question fields

Each daily question must have:

| Field | Rule |
|---|---|
| `id` | Stable, unique ID; never recycle an ID for different wording or an answer change |
| `prompt.en` / `prompt.tr` | Natural, equivalent English and Turkish wording |
| `options.en` / `options.tr` | Same number and same semantic order in both languages |
| `answerIndex` | One zero-based correct option index, identical across locales |
| `category` | One controlled category: science, nature, geography, history, culture, technology, sport, language, arts, everyday, or general |
| `difficulty` | easy, medium, or hard |
| `sourceUrl` | Required for factual questions; direct, credible evidence for the keyed answer |
| `verifiedAt` | Date the answer and source were last checked |
| `editorialStatus` | Only `approved` questions may enter a release pack |

## Editorial gates

- Exactly one unambiguously correct answer. Distractors must be plausible but not arguably correct.
- Avoid trick wording, regional assumptions, stereotypes, sensitive personal topics, unsafe advice and facts likely to become outdated.
- Use primary or authoritative sources where practical. Dynamic facts need a current source and a freshness review date.
- Do not generate trivia live for players. AI may assist drafting and review, but facts and translations require editorial verification before approval.
- Turkish must be written naturally, not mechanically translated. Preserve semantic equivalence and answer-index alignment.
- Balance categories and difficulty across each five-question day and across the 90-day pack.
- Test every pack for unique IDs, four options, valid answer indices, complete locale coverage, source metadata and no duplicate prompts.

## Friend / social-question catalog

- Keep subjective, discussion-friendly questions separate from factual Daily Drop questions.
- Preserve legacy invite compatibility explicitly: v1 remains the original 20-question mapping; v2 remains the currently published 35-question mapping.
- An expanded social catalog must use a new invite version (v3); never change the seed-to-question mapping of v1 or v2 links.
- Social questions have no factual answer index. They still require stable IDs, natural EN/TR copy, four aligned options and editorial approval.
