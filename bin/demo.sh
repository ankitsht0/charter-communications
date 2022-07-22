#!/bin/bash
echo "Creating customers ... "
sh ./create-customers.sh
echo "Adding purchases ..."
sh ./add-purchases.sh
echo "Getting rewards ..."
sh ./get-reward.sh
