在将start.sh复制到linux系统后赋予权限:
修改在windows和linux换行符不一致问题:
chmod u+x *.sh
sed -i "s/\r//" start.sh




mysql容器查看ip:
docker inspect --format='{{.NetworkSettings.IPAddress}}' mysql


在使用权限插件emqx_auth_mysql时配置文件中的auth.mysql.server地址一定要改

如果mysql在本机就写本机的ip 一定不能用127.0.0.1