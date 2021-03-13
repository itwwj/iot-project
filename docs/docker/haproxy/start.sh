#!/bin/bash

docker stop haproxy
docker rm haproxy

docker run --name haproxy \
    -e LANG=en_US.UTF-8  \
    -v `pwd`/conf/haproxy.cfg:/etc/haproxy/haproxy.cfg \
    --restart=always \
    --net host \
    -d haproxy:latest