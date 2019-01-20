package tesis.tenant.util;

import tesis.app.domain.Database;
import com.zaxxer.hikari.HikariDataSource;
import java.net.URI;
import javax.sql.DataSource;

public class DataSourceUtil {
    
    public static DataSource createAndConfigureDataSource(Database tenant) {
        String username = null, password = null, dbUrl = null;
        try {
            URI dbUri = new URI(System.getenv(tenant.getName()));
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setJdbcUrl(dbUrl);
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setConnectionTimeout(20000);
        ds.setMinimumIdle(10);
        ds.setMaximumPoolSize(20);
        ds.setIdleTimeout(300000);
        ds.setConnectionTimeout(20000);
        ds.setPoolName(tenant.getTenantId() + "-connection-pool");
        return ds;
    }
    
}
