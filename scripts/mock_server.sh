#!/usr/bin/env bash

echo "calling mock server"
response=$(curl --write-out "HTTPSTATUS:%{http_code}" --silent --output - --location --request POST "https://58f4a27e-0002-4bff-98a3-45311947892f.mock.pstmn.io/test")

# Extract the body and the status code
http_body=$(echo "$response" | sed -e 's/HTTPSTATUS\:.*//g')
http_status=$(echo "$response" | tr -d '\n' | sed -e 's/.*HTTPSTATUS://')
echo "Response Body: $http_body"

if [[ "http_status" -ge 400 ]]; then
  echo "[ERROR] failed with status code ${http_status}"
  exit 1
fi
