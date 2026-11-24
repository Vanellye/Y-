#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

bash "$ROOT/scripts/validate-provenance.sh"

if MANIFEST_PATH="$ROOT/tests/fixtures/invalid-manifest.json" bash "$ROOT/scripts/validate-provenance.sh" >/dev/null 2>&1; then
  echo "FAIL: invalid fixture unexpectedly passed" >&2
  exit 1
fi

echo "PASS: invalid fixture is rejected by the full JSON Schema validator"
