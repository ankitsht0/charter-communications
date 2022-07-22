#!/bin/bash
curl --location --request POST 'localhost:8080/api/v1/customers/1/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 120.00,
        "timestamp": "2022-07-22T02:41:37.909240026Z"
    },
    {
        "amount": 450.00,
        "timestamp": "2022-05-22T02:41:37.909240026Z"
    },
    {
        "amount": 360.00,
        "timestamp": "2022-06-22T02:41:37.909240026Z"
    },
    {
        "amount": 180.00,
        "timestamp": "2022-07-02T02:41:37.909240026Z"
    }
]'