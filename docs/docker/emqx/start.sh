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
      -v `pwd`/css/app.1495f134b9420661c25a03fc6be1c155.css:/opt/emqx/lib/emqx_dashboard-4.2.3/priv/www/static/css/app.1495f134b9420661c25a03fc6be1c155.css \
      -e EMQX_NAME=emqx \
      -v /etc/localtime:/etc/localtime \
      --privileged=true \
      -u root emqx/emqx:4.2.3

