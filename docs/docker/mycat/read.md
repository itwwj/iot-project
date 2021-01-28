
docker pull registry.cn-hangzhou.aliyuncs.com/ongo360/mycat:1.6.1
### 个人觉得名称太长   给它打个tag   xxx为该镜像的id 可以通过docker images 查看
docker tag  xxx  mycat
docker rmi registry.cn-hangzhou.aliyuncs.com/ongo360/mycat:1.6.1
### 创建 以下目录,为docker数据卷做准备  我这里目录为/root/mycat
mkdir conf
mkdir logs
### docker持久化mycat中的配置文件到conf/logs两个目录下,在我们修改宿主机下的文件时 docker容器中的文件也会同步更新  会减少很多麻烦
docker run -d --name mycat  -p 8066:8066 -v `pwd`/conf:/usr/local/mycat/conf -v `pwd`/logs:/usr/local/mycat/logs  mycat
### 至此 我们mycat容器已经安装并成功运行 我们可以通过docker ps -a 查看所有容器
### 如果我们看见容器状态为 down 或者 exited 
### 我们可以进入 以上创建的logs 目录查看 日志
mycat.log 存储运行日志
wrapper.log 存储启动日志