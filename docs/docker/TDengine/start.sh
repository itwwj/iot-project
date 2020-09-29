#!/bin/bash
cur_dir=`pwd`

docker stop tdengine
docker rm tdengine
docker run --restart=always --name tdengine -d \
    -v `pwd`/conf/:/etc/taos  \
    -v `pwd`/logs/:/var/log/taos \
    -v `pwd`/data/:/var/lib/taos \
    -v /etc/localtime:/etc/localtime \
    -p 6030:6030 -p 6035:6035 -p 6041:6041 -p 6030-6040:6030-6040/udp tdengine/tdengine:2.0.0.0
