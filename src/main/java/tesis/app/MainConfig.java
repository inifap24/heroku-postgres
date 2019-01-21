package tesis.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tesis.app.config.AppConfig;
import tesis.app.config.DataConfig;
import tesis.tenant.config.TenantConfig;

@Configuration
@Import({
    AppConfig.class,
    DataConfig.class,
    TenantConfig.class
})

public class MainConfig {
    
}
