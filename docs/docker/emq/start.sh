#!/bin/bash

docker stop emqx
docker rm emqx
docker run -d --name emqx --restart=always  \
      -p 1883:1883 \
      -p 8083:8083 \
      -p 8883:8883 \
      -p 8084:8084 \
      -p 18083:18083 \
      -v `pwd`/conf/emqx_auth_mysql.conf:/opt/emqx/etc/plugins/emqx_auth_mysql.conf \
      -v `pwd`/conf/emqx.conf:/opt/emqx/etc/emqx.conf \
      -v `pwd`/conf/acl.conf:/opt/emqx/etc/acl.conf \
      -v /etc/localtime:/etc/localtime \
      --privileged=true \
      emqx/emqx:v4.0.0

