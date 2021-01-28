#!/bin/bash
cur_dir=`pwd`
mkdir `pwd`/data
docker stop mongodb
docker rm mongodb
docker run --restart=always --name mongodb \
       -p 27017:27017 \
       -v `pwd`/data/:/data/db  \
       -v `pwd`/conf/:/data/configdb  \
       -v `pwd`/backup/:/data/backup  \
       -v `pwd`/log/:/data/log  \
       -d mongo --auth