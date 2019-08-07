# SpringCloud管理平台

[TOC]

## 简介

​	本项目为Springcloud框架搭建的管理平台

## 项目结构

1. iot-registry------------------------------------eureka注册中心，服务注册与发现
2. iot-config--------------------------------------config配置中心
3. iot-gateway-----------------------------------zuul网关，请求分发
4. iot-authentication--------------------------Oauth2认证中心，服务认证
5. iot-system-------------------------------------系统管理模块，角色，用户，机构，权限等

## 技术栈

1. Springboot、springboot-Admin

2. Springcloud(Eureka+Config+Hystrix(HystrixDashboard)+Zuul+Bus+Sleuth+Ribbon+Turbine+

   Stream+Feign+OAuth2)

3. Spring security+OAuth2

4. pagehelper

5. Mysql+redis+(mongodb)

6. rabbitMQ（bus+rabbit MQ）

7. alibaba druid、fastjson

8. poi

9. swagger2

10. vue

11. pring-boot-admin

12. mybatis-plus