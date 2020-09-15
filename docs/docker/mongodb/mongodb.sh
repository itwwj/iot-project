#!/bin/bash
cur_dir=`pwd`

docker stop mongodb
docker rm mongodb
docker run --restart=always -p 27017:27017 -v /data/docker-data/mongodb-data:/data/db --name mongodb -d mongo