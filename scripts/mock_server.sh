#!/usr/bin/env bash

echo "calling mock server"
response=$(curl --location --request POST 'https://58f4a27e-0002-4bff-98a3-45311947892f.mock.pstmn.io/test')

if [[ "$response" -ge 400 ]]; then
  echo "[ERROR] failed with status code ${response}"
  exit 1
fi
