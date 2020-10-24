#!/bin/bash

docker stop rabbitmq
docker rm rabbitmq

docker run -d --hostname rabbitmq --name rabbitmq --restart=always \
    -e RABBITMQ_DEFAULT_USER=root -e RABBITMQ_DEFAULT_PASS=root \
    -v `pwd`/data//:/var/rabbitmq/lib \
    -p 15672:15672 -p 5672:5672 -p 25672:25672 -p 61613:61613 -p 10883:1883 \
    -e TZ="Asia/Shanghai" \
    rabbitmq:management
