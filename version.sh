#!/bin/bash
cat springboot-freemarker/src/main/resources/banner.txt
# Default version number
DEFAULT_VERSION=2022.0.1
# Connect the input parameter as the new version number
VERSION=${1:-$DEFAULT_VERSION}
# Update version number
mvn versions:set -DgenerateBackupPoms=false -DnewVersion=$VERSION
