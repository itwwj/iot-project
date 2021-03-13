
```
//进入docker中的rabbitmq中
docker exec -it rabbitmq /bin/bash 
//查看用户列表
 rabbitmqctl list_users
 
# 新建用户：rabbitmqctl add_user username password
# 删除用户：rabbitmqctl delete_user username
# 查看用户列表：rabbitmqctl list_users
 
我的:add_user root root (用户名和密码)
 
 授权角色：rabbitmqctl  set_user_tags  User  Tag
# 查看用户角色： 可通过rabbitmqctl list_users查看
# 重新授权，直接重新执行授权命令即可
 
我的: rabbitmqctl set_user_tags guest administrator
 
# 授权单角色
rabbitmqctl  set_user_tags  zhaok monitoring
# 授权多角色
rabbitmqctl  set_user_tags  zhaok monitoring  policymaker
 
rabbitmq的权限控制通过两层来实现，一是vhost的权限，二是确认有权限访问vhost后，对vhost内资源的权限控制（配置，读，写）
 
(1) 设置用户权限
rabbitmqctl  set_permissions  -p  VHostPath  User  ConfP  WriteP  ReadP
(2) 查看(指定hostpath)所有用户的权限信息
rabbitmqctl  list_permissions  [-p  VHostPath]
(3) 查看指定用户的权限信息
rabbitmqctl  list_user_permissions  User
(4)  清除用户的权限信息
rabbitmqctl  clear_permissions  [-p VHostPath]  User
 
 
# 单用户授权
示例：rabbitmqctl set_permissions -p iot root ".*"  ".*"   ".*"
# 说明：给用户root授权 vhost名称为iot的 配置 写入 读取的权限
示例：rabbitmqctl set_permissions -p broker_one producer_one " " ".*" " "
# 说明：给用户producer_one授权 vhost名称为broker_one的 配置 写入 读取的权限
 
 
我的: rabbitmqctl set_permissions -p / guest ".*"  ".*"   ".*" (这根杠杠就是我的vhost啦)
 
 
新建virtual_host: rabbitmqctl add_vhost xxx
撤销virtual_host: rabbitmqctl delete_vhost xxx
查看列表：rabbitmqctl list_vhosts
 
我的:rabbitmqctl add_vhost /
```