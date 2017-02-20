#!/bin/bash

PATTERN="^[[:digit:]]{1,3}\.[[:digit:]]{1,3}\.[[:digit:]]{1,3}\.[[:digit:]]{1,3}[ ][[:digit:]][ ][[:digit:]]{4}\-[[:digit:]]{2}\-[[:digit:]]{2}[ ][[:digit:]]{2}\:[[:digit:]]{2}[ ][[:digit:]]{4}\-[[:digit:]]{2}\-[[:digit:]]{2}[ ][[:digit:]]{2}\:[[:digit:]]{2}$"

while true
do
printf ">"
read CHOICE DETAIL

if [[ $CHOICE == "QUERY" && $DETAIL =~ $PATTERN ]]; then
    javac Query.java
    java Query $DETAIL
elif [[ $CHOICE == "EXIT" ]]; then
    exit 0
else 
    echo Unknown COMMAND
fi
done