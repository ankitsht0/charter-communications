#!/bin/bash
echo "Creating customers ... "
sh ./create-customers.sh
echo
echo "Listing customers ..."
sh ./list-customers.sh
echo
echo "Adding purchases ..."
sh ./add-purchases.sh
echo
echo "Getting rewards ..."
sh ./get-reward.sh
echo
