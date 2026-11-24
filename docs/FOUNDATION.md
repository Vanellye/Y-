# Yaro Foundation

This repository is the standalone home for Ŷæṛö (Yaro). This foundation establishes a language-neutral provenance record before implementation begins.

## Non-negotiable invariants

- Preserve source artifacts; imported material is never silently rewritten.
- Every imported artifact records its origin, immutable revision where available, path or locator, and content digest.
- Every transformation records its inputs, output digest, method, and parent lineage.
- Separate verified facts from derived conclusions, hypotheses, unknowns, and metaphor.
- Treat automation output as a proposal. Human review is required before consequential repository changes, releases, or deployments.
- Develop on feature branches; do not force-update protected history.
- Missing evidence is recorded as unknown, not inferred as success.

## Initial structure

- `provenance/manifest.json`: initial repository provenance record.
- `provenance/manifest.schema.json`: JSON Schema contract for provenance records.
- `scripts/validate-provenance.sh`: dependency-light structural validator (requires Bash and jq).
- `tests/`: positive and negative validation fixtures.
- `.github/workflows/validate.yml`: pull-request and push validation.

## Status boundaries

This is a bootstrap foundation, not a completed Ɣơ compiler, parser, runtime, or semantic authority. No source repository has been imported by this commit. The initial manifest records the repository's own bootstrap commit as the first anchor; later commits must update the manifest with exact immutable references and digests.

## Validation

Run locally:

```bash
bash scripts/validate-provenance.sh
```

The validator checks the manifest's required fields and enumerated evidence/status values. It does not prove the truth of claims, verify remote signatures, or replace independent review.
