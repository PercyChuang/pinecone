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
      