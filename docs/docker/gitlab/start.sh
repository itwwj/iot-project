#!/bin/bash

docker stop gitlab
docker rm gitlab

docker run -d \
    -p 8443:443 -p 9003:9003 -p 222:22 \
    --name gitlab --restart always \
    -v `pwd`/config:/etc/gitlab \
    -v `pwd`/logs:/var/log/gitlab \
    -v `pwd`/data:/var/opt/gitlab \
    --privileged=true beginor/gitlab-ce:11.3.0-ce.0