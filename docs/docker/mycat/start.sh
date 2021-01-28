#!/bin/bash

docker stop mycat
docker rm mycat

docker run -d --name mycat  \
    -p 8066:8066 \
    -v `pwd`/conf:/opt/mycat/conf \
    -v `pwd`/logs:/opt/mycat/logs  \
    mycat