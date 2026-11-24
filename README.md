# Ŷæṛö / Yaro

A standalone project repository for Ŷæṛö (Yaro).

## Purpose

Establish a provenance-preserving foundation for future language, tooling, and research work. The repository begins with explicit evidence classes, immutable source references, reversible transformation lineage, and human-reviewed changes.

## Start here

- [Foundation and invariants](docs/FOUNDATION.md)
- [Provenance manifest](provenance/manifest.json)
- [Manifest schema](provenance/manifest.schema.json)

## Validate

Requires Bash and jq:

```bash
bash scripts/validate-provenance.sh
bash tests/validate-provenance.sh
```

This bootstrap does not claim that a Ɣơ compiler, parser, runtime, or imported source corpus exists yet.
