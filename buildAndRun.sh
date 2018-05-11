#!/bin/sh
mvn clean package && docker build -t de.keo9ren/valueator .
docker rm -f valueator || true && docker run -d -p 8080:8080 -p 4848:4848 --name valueator de.keo9ren/valueator
