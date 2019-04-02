#!/bin/sh

if [ ! -d target ]; then
	mvn package
fi
java -jar target/SNC-2.1.0-SNAPSHOT-jar-with-dependencies.jar
