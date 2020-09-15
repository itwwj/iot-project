#!/bin/bash
cur_dir=`pwd`

docker stop mysql
docker rm mysql
docker run --name mysql --restart=always \
    -v `pwd`/conf:/etc/mysql/conf.d \
    -v `pwd`/data/:/var/lib/mysql \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD="root" \
    -e TZ=Asia/Shanghai \
    -d mysql:5.7.9
