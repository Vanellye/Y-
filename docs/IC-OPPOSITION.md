# I.C. Opposition

## Purpose

I.C. Opposition is the diagnostic validation discipline for this repository. It challenges a proposed claim or implementation using repeatable checks, independent representations, shared test vectors, and explicit failure reporting.

Opposition is not blind reversal, adversarial obstruction, or an autonomous repair authority.

## Operating rules

1. **Challenge, do not negate by default.** A check must identify the claim or invariant it tests.
2. **Preserve evidence.** Keep source revisions, test-vector versions, commands, and results traceable.
3. **Make outcomes explicit.** Report PASS, FAIL, BLOCKED, or NOT_RUN. Never convert missing evidence into PASS.
4. **Keep checks reproducible.** Use deterministic fixtures and versioned shared vectors wherever possible.
5. **Separate diagnosis from repair.** Findings may propose a repair, but this workflow does not rewrite source files or authorize merges.
6. **Preserve human control.** Repository writes, merges, releases, and privileged actions remain subject to explicit human authorization.

## Workflow mapping

The GitHub Actions workflow named `I.C. Opposition — Yaro foundation validation` preserves the prior automated behavior:
- JVM JSON Schema and provenance validation
- negative test confirming invalid manifests are rejected
- Kotlin Ɣơ adapter execution against the shared versioned vectors

The workflow name changes the diagnostic framing, not the meaning of the underlying tests.
