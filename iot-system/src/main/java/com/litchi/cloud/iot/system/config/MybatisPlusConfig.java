package com.litchi.cloud.iot.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/** 
 *分页配置
 * @author: tievd(wjhf)
 * @date: 2019年8月10日 下午11:52:13
 * @vesion: 0.0.1
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.litchi.cloud.iot.system.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}