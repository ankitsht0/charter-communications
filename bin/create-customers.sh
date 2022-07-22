#!/bin/bash
curl --location --request POST 'localhost:8080/api/v1/customers' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "name": "customer-1"
    },
    {
        "name": "customer-2"
    },
    {
        "name": "customer-3"
    },
    {
        "name": "customer-4"
    }
]'