package com.example.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource masterDataSource(DataConfig dbProperties) {
        HikariDataSource ds = new HikariDataSource();
//        ds.setUsername(dbProperties.getUsername());
//        ds.setPassword(dbProperties.getPassword());
        ds.setJdbcUrl(dbProperties.getUrl());
        ds.setDriverClassName(dbProperties.getDriverClassName());
        ds.setPoolName(dbProperties.getPoolName());
        ds.setMaximumPoolSize(dbProperties.getMaxPoolSize());
        ds.setMinimumIdle(dbProperties.getMinIdle());
        ds.setConnectionTimeout(dbProperties.getConnectionTimeout());
        ds.setIdleTimeout(dbProperties.getIdleTimeout());
        return ds;
    }
}