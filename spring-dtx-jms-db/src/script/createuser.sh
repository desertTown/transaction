#!/usr/bin/env bash

curl -X POST -d '{"username": "nick", "deposit": 10}' -H 'Content-Type: application/json' http://localhost:8080/api/customer