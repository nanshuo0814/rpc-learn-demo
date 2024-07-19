# RPC 技术框架学习 Demo

## 项目介绍

基于 Java + Etcd + Vert.x 的高性能 RPC 框架，支持多种序列化器、多种协议、多种负载均衡算法、多种注册中心、多种设计模式等。

项目分为简易版和扩展版

- 简易版：rpc-simplify-demo
  - rpc-simplify-code：RPC 框架简易版核心代码
  - rpc-simplify-common：RPC 公用模块
  - rpc-simplify-consumer：RPC 服务消费者
  - rpc-simplify-provider：RPC 服务提供者
- 扩展版：rpc-extend-demo
  - rpc-extend-code：RPC 框架扩展版核心代码
  - rpc-extend-common：RPC 公用模块
  - rpc-springboot-consumer：RPC 服务消费者（Spring Boot 框架）
  - rpc-springboot-provider：RPC 服务提供者（Spring Boot 框架）
  - rpc-spring-boot-starter：注解驱动的 RPC 框架，可在 Spring Boot 项目中快速使用

## 技术选型

### 后端

后端技术以 Java 为主，但所有的思想和设计都是可以复用到其他语言的，代码不同罢了。

- ⭐️ Vert.x 框架
- ⭐️ Etcd 云原生存储中间件（jetcd 客户端）
- ZooKeeper 分布式协调工具（curator 客户端）
- ⭐️ SPI 机制
- ⭐️ 多种序列化器
  - JSON 序列化
  - Kryo 序列化
  - Hessian 序列化
- ⭐️ 多种设计模式
  - 双检锁单例模式
  - 工厂模式
  - 代理模式
  - 装饰者模式
- ⭐️ Spring Boot Starter 开发
- 反射和注解驱动
- Guava Retrying 重试库
- JUnit 单元测试
- Logback 日志库
- Hutool、Lombok 工具库