#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
MANIFEST="$ROOT/provenance/manifest.json"
SCHEMA="$ROOT/provenance/manifest.schema.json"

command -v jq >/dev/null 2>&1 || {
  echo "ERROR: jq is required" >&2
  exit 2
}
[[ -s "$MANIFEST" ]] || { echo "ERROR: manifest missing or empty" >&2; exit 1; }
[[ -s "$SCHEMA" ]] || { echo "ERROR: schema missing or empty" >&2; exit 1; }

jq -e '
  .schema_version == "1.0.0" and
  .project.name == "Ŷæṛö" and
  .project.repository == "Vanellye/Y-" and
  (.created_at | type == "string" and length > 0) and
  (.evidence_class | IN("VERIFIED","DERIVED","HYPOTHESIS","SUBJECTIVE","METAPHOR","UNKNOWN","CONTRADICTED")) and
  (.status | IN("PROPOSED","APPLIED","VERIFIED","FAILED","NOT_RUN","BLOCKED")) and
  (.sources | type == "array") and
  (.artifacts | type == "array") and
  (.transformations | type == "array") and
  all(.sources[]; (.source_id | type == "string" and length > 0) and (.locator | type == "string" and length > 0) and (.evidence_class | IN("VERIFIED","DERIVED","HYPOTHESIS","SUBJECTIVE","METAPHOR","UNKNOWN","CONTRADICTED"))) and
  all(.artifacts[]; (.artifact_id | type == "string" and length > 0) and (.path | type == "string" and length > 0) and (.status | IN("PROPOSED","APPLIED","VERIFIED","FAILED","NOT_RUN","BLOCKED"))) and
  all(.transformations[]; (.transformation_id | type == "string" and length > 0) and (.inputs | type == "array") and (.output | type == "string" and length > 0) and (.method | type == "string" and length > 0) and (.status | IN("PROPOSED","APPLIED","VERIFIED","FAILED","NOT_RUN","BLOCKED")))
' "$MANIFEST" >/dev/null

echo "PASS: provenance manifest is valid against the bootstrap structural contract"
