# Ŷæṛö / Yaro

A standalone project repository for Ŷæṛö (Yaro).

## Purpose

Establish a provenance-preserving foundation with a standards-backed JSON Schema validation engine.

## I.C. Opposition

The repository's automated validation workflow is named **I.C. Opposition**. Opposition is a diagnostic instrument: it challenges claims, runs independent checks, exposes discrepancies, and records failures. It is not blind reversal and does not authorize automatic code changes.

The workflow retains its existing automated build, schema/provenance checks, negative tests, and Ɣơ adapter vector checks. A failed check remains a failure; a missing or unexecuted check is not reported as a pass.

See [I.C. Opposition operating principles](docs/IC-OPPOSITION.md).

## Start here

- [Foundation and invariants](docs/FOUNDATION.md)
- [Validation trust model](docs/VALIDATION-TRUST-MODEL.md)
- [I.C. Opposition operating principles](docs/IC-OPPOSITION.md)
- [Schema bootstrap validator](validator/README.md)
- [Provenance manifest](provenance/manifest.json)
- [Manifest schema](provenance/manifest.schema.json)

## Validate

Requires JDK 11+ and Maven:

```bash
bash scripts/validate-provenance.sh
bash tests/validate-provenance.sh
```

The validator establishes schema conformance, not factual truth or authorization. Ɣơ implementation parity remains a conformance milestone.
