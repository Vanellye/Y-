# Yaro Foundation

This repository is the standalone home for Ŷæṛö (Yaro). This foundation establishes a provenance-preserving record and a standards-backed validation reference.

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
- `provenance/manifest.schema.json`: JSON Schema Draft 2020-12 contract.
- `validator/`: pinned JVM JSON Schema reference engine and tests.
- `scripts/validate-provenance.sh`: bootstrap entry point for full schema validation.
- `tests/`: positive and negative validation fixtures.
- `.github/workflows/validate.yml`: pull-request and push validation.

## Status boundaries

The validator is a schema-conformance authority for its pinned engine, dialect, configuration, and resolved resources. It is not a factual truth oracle, provenance authenticator, or authorization system. No Ɣơ compiler, parser, runtime, or imported source corpus is claimed by this foundation.

## Validation

Requires JDK 11+ and Maven:

```bash
bash scripts/validate-provenance.sh
```
