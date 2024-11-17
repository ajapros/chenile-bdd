#!/bin/bash

if [[ -z $1 ]]
then
	echo "Usage: $0 new-version"
	exit 1
fi

git add .
git commit -m "Bump up to $1"
git push origin main

exit 0
