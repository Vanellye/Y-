# Ŷæṛö / Yaro

A standalone project repository for Ŷæṛö (Yaro).

## Purpose

Establish a provenance-preserving foundation with a standards-backed JSON Schema validation engine.

## Start here

- [Foundation and invariants](docs/FOUNDATION.md)
- [Validation trust model](docs/VALIDATION-TRUST-MODEL.md)
- [Schema bootstrap validator](validator/README.md)
- [Provenance manifest](provenance/manifest.json)
- [Manifest schema](provenance/manifest.schema.json)

## Validate

Requires JDK 11+ and Maven:

```bash
bash scripts/validate-provenance.sh
bash tests/validate-provenance.sh
```

The validator establishes schema conformance, not factual truth or authorization. Ɣơ implementation parity remains a future conformance milestone.
