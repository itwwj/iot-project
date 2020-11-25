#!/bin/bash

docker stop emqx
docker rm emqx
docker run -d --name emqx --restart=always  \
      -p 1883:1883 \
      -p 8083:8083 \
      -p 8883:8883 \
      -p 8084:8084 \
      -p 18083:18083 \
      -v `pwd`/etc:/opt/emqx/etc \
      -v `pwd`/log:/opt/emqx/log \
      -v `pwd`/css/app.86d4a07f05ad93ba771b4d37ffeee08d.css:/opt/emqx/lib/emqx_dashboard-4.0.0/priv/www/static/css/app.86d4a07f05ad93ba771b4d37ffeee08d.css \
      -e EMQX_NAME=emqx \
      -v /etc/localtime:/etc/localtime \
      --privileged=true \
      -u root emqx/emqx:v4.0.0

