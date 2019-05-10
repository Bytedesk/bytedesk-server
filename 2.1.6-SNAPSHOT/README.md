# 萝卜丝·服务器部署说明

## 准备

- [Nginx](https://github.com/Bytedesk/bytedesk-server/wiki/nginx)
- [MySQL](https://github.com/Bytedesk/bytedesk-server/wiki/mysql)
- [Redis](https://github.com/Bytedesk/bytedesk-server/wiki/redis)
- [RabbitMQ](https://github.com/Bytedesk/bytedesk-server/wiki/rabbitmq)
- [Zookeeper](https://github.com/Bytedesk/bytedesk-server/wiki/zookeeper)

## 配置

### 服务器端

#### [下载](https://github.com/Bytedesk/bytedesk-server/releases/download/v2.1.5-alpha/2.1.5-SNAPSHOT.zip)

#### 解压

#### 修改配置config/application.properties，将以下值均修改为自定义服务器配置

- MySQL

``` bash
# mysql 5.7
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/数据库名?autoReconnect=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=
```

- Redis

``` bash
# redis缓存
spring.cache.type=Redis
spring.redis.database=0
# Redis服务器地址
spring.redis.host=
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
```

- 上传文件

支持服务器本地存储、阿里云OSS、腾讯云对象存储三种存储方式，其中本地存储不支持集群，下面说明以腾讯云为例

``` bash
# 腾讯云cos
upload.type=tencent
# 创建bucket并需要在此bucket下创建文件夹：apns/development(二级文件夹), apns/production(二级文件夹), avatars, images, voices, files
# 存储桶所属地域
tencent.bucket.location=
# 存储桶名称
tencent.bucket.name=
# 访问域名
tencent.bucket.domain=

#API密钥管理获取
tencent.appid=
tencent.secretid=
tencent.secretkey=
```

- RabbitMQ

``` bash
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=用户名
spring.rabbitmq.password=密码
spring.rabbitmq.stomp.port=61613
spring.rabbitmq.stomp.login=stomp_web
spring.rabbitmq.stomp.passcode=stomp_web
```

- ZooKeeper

``` bash
zk.url=127.0.0.1:2181
```

- 上传到服务器
- 修改start.sh权限为可执行权限，如：

``` bash
chmod 777 start.sh
```

- 启动：

``` bash
./start.sh
```

- 查看是否启动成功

``` bash
方法1：
等待5分钟后，执行 netstat -ntlp, 查看8000端口号是否启动，如有，则启动成功
方法2：
浏览器中输入 http://ip:8000/hello 查看是否有返回，如有，则启动成功
```

#### 修改nginx负载均衡配置，指向此服务器地址

客户端均通过nginx访问rest接口，nginx需要支持https和websocket, 具体可参考[Nginx](https://github.com/Bytedesk/bytedesk-server/wiki/nginx)

#### 如需配置集群，可在多台机器上重复上述步骤即可

#### 后续新版升级步骤

注意：为了不影响集群整体可用性，建议一台一台升级，也即只有第一台升级成功之后，再按照下述步骤升级第二台

``` bash
查看进程：netstat -ntlp，找到8000对应进程号
杀掉进程：kill -9 进程号
上传新版：jar文件
启动：./start.sh
```

### 安卓端

- 参考demo中：自定义服务器
- REST服务器为nginx地址
- 其中消息服务器地址为RabbitMQ服务器地址

### iOS端

- 参考demo中：自定义服务器
- REST服务器为nginx地址
- 其中消息服务器地址为RabbitMQ服务器地址

### web端

- 修改js/data.js中默认HOST地址为nginx地址

## 其他

- [网址](https://github.com/Bytedesk/bytedesk-server/wiki)
