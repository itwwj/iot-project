#!/bin/bash

docker stop zk
docker rm zk

docker run --name zk --restart always \
      -p 2181:2181 -p 2888:2888 -p 3888:3888 \
      --network host \
      -v `pwd`/conf/zoo.cfg:/conf/zoo.cfg \
      -v `pwd`/data:/data \
      -v `pwd`/log:/datalog \
      -d docker.io/zookeeper