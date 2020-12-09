1.运行start.sh
2.查看容器 docker ps |grep mongo
3.使用mongodb shell客户端 docker exec -it mongodb mongo admin
4.添加用户名密码 db.createUser({user:'admin',pwd:'public',roles:[{role:'root',db:'admin'}],})
5.使用新创建的用户 db.auth('admin', 'public')