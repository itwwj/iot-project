1.运行monogodb.sh
2.查看容器 docker ps
3.使用mongodb shell客户端 docker exec -it mongodb mongo admin
4.添加用户名密码 db.createUser({ user:'root',pwd:'root',roles:[ { role:'userAdminAnyDatabase', db: 'admin'}]});
5.使用新创建的用户 db.auth('root', 'root')