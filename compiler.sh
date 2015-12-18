#!/bin/bash

find -name "*.java" > sources.txt && javac -d classes @sources.txt && rm -f sources.txt
