#!/bin/bash

docker stop zk
docker rm zk

docker run --name zk --restart always \
      -p 2181:2181 -p 2888:2888 -p 3888:3888 \
      --network mynetwork \
      --ip 172.18.0.5 \
      -v `pwd`/conf:/conf \
      -v `pwd`/data:/data \
      -v `pwd`/log:/logs \
      -d zookeeper