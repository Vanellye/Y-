# Validation Trust Model

## Role of the bootstrap validator

The Yaro bootstrap validator is a standards-backed reference for JSON Schema conformance. It is authoritative only for the outcome produced by its pinned implementation, dialect, configuration, and resolved schema resources.

## Evidence returned

- `SCHEMA_VALID`: the instance passed the selected schema assertions.
- `INSTANCE_INVALID`: the instance failed one or more assertions.
- `SCHEMA_INVALID`: the schema failed meta-schema validation when requested.
- `VALIDATOR_ERROR`: validation could not be completed; this is not a pass.

## It is not a truth oracle

Schema conformance does not prove the truth of values or claims. It does not authenticate sources, verify hashes against an independent origin, establish authorship, or grant authorization. These remain separate verification and governance stages.

## Independent implementation path

Ɣơ may implement parsing, schema evaluation, and provenance semantics independently. During bootstrap, the JVM reference engine remains the comparison oracle for schema outcomes. Conformance claims require pinned official test-suite evidence, documented exclusions, and repeatable cross-engine comparisons.
