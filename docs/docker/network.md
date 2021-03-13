创建network：
docker network create --subnet=172.18.0.0/16 mynetwork

查看网络：
docker network ls

使用网络：
 docker run -itd --name networkTest1 --net mynetwork --ip 172.18.0.2 centos:latest /bin/bash