package tesis.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.datasource")
@Data
public class DataConfig {
    
    private String url;
//    private String username;
//    private String password;
    private String driverClassName;
    private long connectionTimeout;
    private int maxPoolSize;
    private long idleTimeout;
    private int minIdle;
    private String poolName;

}
