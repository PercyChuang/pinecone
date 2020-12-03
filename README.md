# 松果系统
    分布式微服务架构 
    1.使用 nacos作为服务注册中心
    2.服务框架：spring-cloud-alibaba
    
## 开发注意点
    请务必遵守以下开发注意点
   
### 数据库建表
    1.所有表必须字段（要表、关系表）
        字段名 id           类型：bigint(64)    注释：主键
        字段名 create_time  类型：datetime(0)  注释：创建时间 
        
    2.主表必须字段
        字段名 update_time  类型：datetime(0)  注释：修改时间
        字段名 logic_flag   类型：int(1)        注释：删除状态 0 未删除 1 已删除
        
### SQL
    1.数据库表起别名
        例子：sys_user AS sy_us
    
    
### 注册服务
    1，发布配置
        首先通过调用 Nacos Open API 向 Nacos Server 发布配置：
        dataId 为 ${prefix}-${spring.profile.active}.${file-extension}


### 服务端口号
    1. nacos (注册与配置中心)               8848
    2. pinecone-gateway (网关)            8080
    3. pinecone-auth (授权与鉴权)          8081
    4. pinecone-common (公共服务)          8082
    5. pinecone-user (用户服务)            8083
    6. pinecone-pay (支付服务)             8084
    7. pinecone-mq                       8092(测试服务)
    8. pinecone-netty                    8093(测试服务)
    
    9. sentinel-dashboard (流控控制台)      8180 
    10. seata-server (分布式事务管理器)      9200     