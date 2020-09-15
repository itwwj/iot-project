#!/bin/bash

docker stop redis
docker rm redis
docker run -idt -p 6379:6379 --name redis --restart=always \
    -v `pwd`/redis.conf:/etc/redis/redis_default.conf \
    -v `pwd`/data/:/data \
    -e TZ="Asia/Shanghai" \
    redis:4.0.12 redis-server /etc/redis/redis_default.conf --appendonly yes

