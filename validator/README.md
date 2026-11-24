# Yaro Schema Bootstrap Validator

This is the standards-backed reference validator for JSON Schema, using NetworkNT 2.0.4 (Java 8+ / Jackson 2.x) and Joni for ECMA-262-compatible regular-expression handling.

## Run

Requires JDK 11+ and Maven:

```bash
mvn -f validator/pom.xml test
mvn -f validator/pom.xml exec:java -Dexec.args="schema.json instance.json --check-schema"
```

The repository bootstrap command is:

```bash
bash scripts/validate-provenance.sh
```

Exit codes: `0` valid, `1` schema/instance invalid, `2` usage or infrastructure error.

## Dialects and behavior

- Default dialect: Draft 2020-12.
- An explicit `$schema` dialect is honored by the library.
- Format assertions are explicitly enabled for instance validation.
- Joni is configured for ECMA-262 regular-expression compatibility.
- `--check-schema` validates the schema against the Draft 2020-12 meta-schema before validating the instance.

## Trust boundary

A passing result means only that the instance conforms to the selected JSON Schema dialect and enabled assertion configuration. It does not prove factual truth, source authenticity, authorship, or authorization. A schema's own trustworthiness and external reference resolution remain separate concerns.

NetworkNT is an external Apache-2.0 reference implementation, not Yaro-authored validator code. Ɣơ can use this engine as a differential reference while an independent implementation is developed. Claiming Ɣơ parity requires running the official JSON Schema Test Suite, documenting unsupported vocabularies and resolver behavior, and comparing outcomes repeatably.
