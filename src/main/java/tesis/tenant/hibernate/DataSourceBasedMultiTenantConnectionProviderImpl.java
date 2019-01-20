package tesis.tenant.hibernate;

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