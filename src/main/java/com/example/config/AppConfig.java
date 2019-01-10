package com.example.config;

import com.zaxxer.hikari.HikariDataSource;
import java.net.URI;
import java.net.URISyntaxException;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource(DataConfig dbProperties) throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setJdbcUrl(dbUrl);
        ds.setDriverClassName(dbProperties.getDriverClassName());
        ds.setPoolName(dbProperties.getPoolName());
        ds.setMaximumPoolSize(dbProperties.getMaxPoolSize());
        ds.setMinimumIdle(dbProperties.getMinIdle());
        ds.setConnectionTimeout(dbProperties.getConnectionTimeout());
        ds.setIdleTimeout(dbProperties.getIdleTimeout());
        return ds;
    }
}