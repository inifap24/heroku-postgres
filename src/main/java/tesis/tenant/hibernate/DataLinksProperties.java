package tesis.tenant.hibernate;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("datasource")
@Data
public class DataLinksProperties {
    
    private Map<String, String> datalinks;
    
}
