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
curl --location --request POST 'localhost:8080/api/v1/customers/2/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 720.00,
        "timestamp": "2022-06-20T02:41:37.909240026Z"
    }
]'
curl --location --request POST 'localhost:8080/api/v1/customers/3/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 90.00,
        "timestamp": "2022-05-05T02:41:37.909240026Z"
    }
]'
curl --location --request POST 'localhost:8080/api/v1/customers/4/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 540.00,
        "timestamp": "2022-07-18T02:41:37.909240026Z"
    }
]'