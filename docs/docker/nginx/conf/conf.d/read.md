``` 
 upstream emqx_servers {
      server 192.168.1.16:1883  max_fails=3 fail_timeout=60s;
      server 192.168.1.17:1883  max_fails=3 fail_timeout=60s;
      keepalive 20000;
  }

  server {
  
  \#防止502以及Timeout的发生
      proxy_connect_timeout 75;
      proxy_read_timeout 300;
      proxy_send_timeout 300;
      proxy_buffer_size 64k;
      proxy_buffers   4 64k;
      proxy_busy_buffers_size 128k;
      proxy_temp_file_write_size 128k;
      listen 8883;
      proxy_pass emqx_servers;
  }
```
```
weight=number 设定服务器的权重，默认是1。
max_fails=number 设定Nginx与服务器通信的尝试失败的次数 
fail_timeout=time 设定统计失败尝试次数的时间段 


proxy_connect_timeout 默认是60s,设置与后端服务器建立连接的超时时间。应该注意这个超时一般不可能大于75秒。
proxy_send_timeout 默认60s, 定义向后端服务器传输请求的超时 
proxy_buffer_size 设置缓冲区的大小为size 
proxy_buffers  为每个连接设置缓冲区的数量为number 
proxy_busy_buffers_size 当开启缓冲响应的功能以后，在没有读到全部响应的情况下，写缓冲到达一定大小时，nginx一定会向客户端发送响应，直到缓冲小于此值
proxy_temp_file_write_size 在开启缓冲后端服务器响应到临时文件的功能后，设置nginx每次写数据到临时文件的size(大小)限制

```

































