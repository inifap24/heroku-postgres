package tesis.tenant.hibernate;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import tesis.tenant.util.DataSourceUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.hibernate.service.spi.Stoppable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl
        implements Stoppable {

    private static final long serialVersionUID = 1L;
    private final DataLinksProperties datalinks;
    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    @Autowired
    public DataSourceBasedMultiTenantConnectionProviderImpl(DataLinksProperties datalinks) {
        this.datalinks = datalinks;
        datalinks.getDatalinks().forEach((k, v) -> addDatalink(k, v));
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSourceMap.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantId) {
        return dataSourceMap.get(tenantId);
    }

    private void addDatalink(String tenantId, String datalink) {
        dataSourceMap.put(tenantId,
                DataSourceUtil.createAndConfigureDataSource(datalink));
    }

    @Override
    public void stop() {
        if (dataSourceMap != null) {
            dataSourceMap.clear();
            dataSourceMap = null;
        }
    }

}

//        datalinks.add("HEROKU_POSTGRESQL_BLACK_URL");
//        datalinks.add("HEROKU_POSTGRESQL_BLUE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_BRONZE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_BROWN_URL");
//        datalinks.add("HEROKU_POSTGRESQL_CHARCOAL_URL");
//        datalinks.add("HEROKU_POSTGRESQL_COBALT_URL");
//        datalinks.add("HEROKU_POSTGRESQL_COPPER_URL");
//        datalinks.add("HEROKU_POSTGRESQL_CRIMSON_URL");
//        datalinks.add("HEROKU_POSTGRESQL_CYAN_URL");
//        datalinks.add("HEROKU_POSTGRESQL_GOLD_URL");
//        datalinks.add("HEROKU_POSTGRESQL_GRAY_URL");
//        datalinks.add("HEROKU_POSTGRESQL_GREEN_URL");
//        datalinks.add("HEROKU_POSTGRESQL_IVORY_URL");
//        datalinks.add("HEROKU_POSTGRESQL_JADE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_MAROON_URL");
//        datalinks.add("HEROKU_POSTGRESQL_MAUVE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_NAVY_URL");
//        datalinks.add("HEROKU_POSTGRESQL_OLIVE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_ONYX_URL");
//        datalinks.add("HEROKU_POSTGRESQL_ORANGE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_PINK_URL");
//        datalinks.add("HEROKU_POSTGRESQL_PUCE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_PURPLE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_RED_URL");
//        datalinks.add("HEROKU_POSTGRESQL_ROSE_URL");
//        datalinks.add("HEROKU_POSTGRESQL_SILVER_URL");
//        datalinks.add("HEROKU_POSTGRESQL_TEAL_URL");
//        datalinks.add("HEROKU_POSTGRESQL_TEAL_URL");
//        datalinks.add("HEROKU_POSTGRESQL_YELLOW_URL");
