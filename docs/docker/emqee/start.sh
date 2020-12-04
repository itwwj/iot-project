#!/bin/bash

docker stop emqx
docker rm emqx
docker run -d --name emqx --restart=always  \
      -p 1883:1883 \
      -p 8083:8083 \
      -p 8883:8883 \
      -p 8084:8084 \
      -p 18083:18083 \
      -v `pwd`/license/emqx.lic:/opt/emqx/etc/emqx.lic \
      -v `pwd`/etc/:/opt/emqx/etc \
      -v `pwd`/log:/opt/emqx/log \
      -v /etc/localtime:/etc/localtime \
      -u root emqx/emqx-ee:4.2.1