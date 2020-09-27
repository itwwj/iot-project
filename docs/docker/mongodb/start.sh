#!/bin/bash
cur_dir=`pwd`

docker stop mongodb
docker rm mongodb
docker run --restart=always --name mongodb \
       -p 27017:27017 \
       -v `pwd`/data/:/data/db  \
       -d mongo