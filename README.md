
# spring cloud 生态探索教程 spring-cloud-side

## 项目说明

-  #### spring-cloud-learn 部分是为了学习Spring cloud ，而精心准备的例子。
- #### 其他部分规划是利用spring cloud 做些具体的项目，围绕一般互联网公司都不可或缺的基础设施部分

## 启动说明

#### 如果是不依赖中间件的模块，单独运行main 方法即可运行
#### 如果有依赖redis，zookeeper 等的模块，建议用docker 启动，同时绑host 访问
- host：
```127.0.0.1 zk.spring.com
   127.0.0.1 redis.spring.com
   127.0.0.1 rabbitmq.spring.com
   127.0.0.1 kafka.spring.com
   127.0.0.1 es.spring.com
   127.0.0.1 m.db.spring.com
   127.0.0.1 s.db.spring.com
   127.0.0.1 mongodb.spring.com
   ```
- docker：docker学习详见https://mongoding.github.io/2017/11/16/docker/  ，启动脚本见script 目录

```
脚本整理中
```
- 端口号分配

```
提供者：7000
注册中心：8000
配置中心：8100
网关：8200
hystrix：8300
zipkin：8400
turbine：8500
消费者：9000
```


## 版本信息

- jdk：>= jdk8   jdk9 可运行
- spring boot ：Spring boot 2.1
- spring cloud: Finchley.RC1

各jar 包版本基本会保持与最新的版本同步更新升级


## 资源地址(欢迎给star或fork)

#### github 地址：https://github.com/mongoding/spring-cloud-side

#### spring boot 脑图地址：https://github.com/mongoding/brain-map-collection/tree/master/%E5%AD%A6%E4%B9%A0

#### 博客地址：https://mongoding.github.io/

## 推荐学习地址：
#### spring cloud: http://www.spring4all.com/





