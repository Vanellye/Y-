#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
bash "$ROOT/scripts/validate-provenance.sh"

if jq -e '.schema_version == "1.0.0" and .project.name == "Ŷæṛö"' "$ROOT/tests/fixtures/invalid-manifest.json" >/dev/null 2>&1; then
  echo "FAIL: invalid fixture unexpectedly passed" >&2
  exit 1
fi

echo "PASS: invalid fixture is rejected by required-field contract"
