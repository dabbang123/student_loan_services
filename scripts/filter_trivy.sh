#!/bin/bash
echo "[*] Filtering HIGH/CRITICAL vulnerabilities in prod-facing modules..."

jq -r '
  .Results[]?.Vulnerabilities[]? |
  select(.Severity == "HIGH" or .Severity == "CRITICAL") |
  select(.PkgPath | test("controller|service|security")) |
  "\(.PkgName) - \(.Severity) - \(.PkgPath)"
' trivy-fs-report.json > filtered-alerts.txt

count=$(cat filtered-alerts.txt | wc -l)

if [ "$count" -gt 0 ]; then
  echo "[!] $count critical vulnerabilities found in key modules:"
  cat filtered-alerts.txt
  exit 1
else
  echo "[*] No critical vulnerabilities in production code."
fi
