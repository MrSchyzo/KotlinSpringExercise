#!/usr/bin/env bash

echo "Building JAR" && \
mvn clean install && \

echo "Rebooting new mysql container" ;
docker stop mysql-rgichallenge ;
docker rm mysql-rgichallenge ;
docker run -d -e MYSQL_ROOT_PASSWORD=root --name mysql-rgichallenge -e MYSQL_DATABASE=tasks -p 3306:3306 mysql:5.7 && \

echo "Waiting MySql server for a little bit" && \
sleep 10 && \

echo "Running JAR" && \
java -jar target/rgi-challenge-0.0-SNAPSHOT.jar