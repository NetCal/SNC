#!/bin/sh

# This script runs the jar-file.
# If the jar-file is not present, it will compile using maven.
# Note that if the source has changed, the project will not recompile until the jar-file
# is removed.

set -ue

VERSION=$(cat pom.xml | paste -s - | grep -Eoi '<groupId>org.networkcalculus.snc.*?/version' | sed 's:.*version>\(.*\)</version.*:\1:g')
TARGET_JAR="target/SNC-${VERSION}-jar-with-dependencies.jar"
if [ ! -f "$TARGET_JAR" ]; then
	mvn package
fi

java -jar "$TARGET_JAR"

