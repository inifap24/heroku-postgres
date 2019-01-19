package tesis.tenant.util;

import tesis.app.domain.Tenant;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DataSourceUtil {
    
    public static DataSource createAndConfigureDataSource(Tenant tenant) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(tenant.getUsername());
        ds.setPassword(tenant.getPassword());
        ds.setJdbcUrl(tenant.getUrl());
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setConnectionTimeout(20000);
        ds.setMinimumIdle(10);
        ds.setMaximumPoolSize(20);
        ds.setIdleTimeout(300000);
        ds.setConnectionTimeout(20000);
        ds.setPoolName(tenant.getTenantId() + "-connection-pool");
        return ds;
    }
    
}
