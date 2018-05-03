package com.topweshare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

@Configuration
public class MysqlStorageConfig {


    @Bean
    @Profile("zipkin-mysql")
    public MySQLStorage mySQLStorage(DataSource datasource) {
        return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
    }
}
