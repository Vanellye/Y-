#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
MANIFEST="${MANIFEST_PATH:-$ROOT/provenance/manifest.json}"
SCHEMA="$ROOT/provenance/manifest.schema.json"

command -v java >/dev/null 2>&1 || { echo "ERROR: JDK 11+ is required" >&2; exit 2; }
command -v mvn >/dev/null 2>&1 || { echo "ERROR: Maven is required" >&2; exit 2; }
[[ -s "$MANIFEST" ]] || { echo "ERROR: manifest missing or empty: $MANIFEST" >&2; exit 1; }
[[ -s "$SCHEMA" ]] || { echo "ERROR: schema missing or empty" >&2; exit 1; }

mvn -q -f "$ROOT/validator/pom.xml" test
mvn -q -f "$ROOT/validator/pom.xml" exec:java \
  -Dexec.args="$SCHEMA $MANIFEST --check-schema"

echo "PASS: schema and manifest validated by the pinned JSON Schema reference engine"
