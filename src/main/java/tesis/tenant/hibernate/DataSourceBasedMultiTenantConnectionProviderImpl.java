package tesis.tenant.hibernate;

import tesis.app.domain.Tenant;
import tesis.tenant.util.DataSourceUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.hibernate.service.spi.Stoppable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tesis.app.persistence.TenantRepository;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl
        implements Stoppable {

    private static final long serialVersionUID = 1L;
    private final TenantRepository tenantRepo;
    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    @Autowired
    public DataSourceBasedMultiTenantConnectionProviderImpl(TenantRepository tenantRepo) {
        this.tenantRepo = tenantRepo;
        if (dataSourceMap.isEmpty()) {
            getTenants();
        }
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSourceMap.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        if (!dataSourceMap.containsKey(tenantIdentifier)) {
            addTenant(tenantRepo.findByTenantId(tenantIdentifier));
        }
        return dataSourceMap.get(tenantIdentifier);
    }

    private void getTenants() {
        Iterable<Tenant> tenants = tenantRepo.findAll();
        for (Tenant tenant : tenants) {
            addTenant(tenant);
        }
    }

    private void addTenant(Tenant tenant) {
        dataSourceMap.put(tenant.getTenantId(),
                DataSourceUtil.createAndConfigureDataSource(tenant));
    }

    @Override
    public void stop() {
        if (dataSourceMap != null) {
            dataSourceMap.clear();
            dataSourceMap = null;
        }
    }

}
