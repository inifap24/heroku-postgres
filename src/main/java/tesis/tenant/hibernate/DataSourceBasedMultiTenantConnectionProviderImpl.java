package tesis.tenant.hibernate;

import com.google.common.collect.Iterables;
import tesis.app.domain.Datalink;
import tesis.tenant.util.DataSourceUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.hibernate.service.spi.Stoppable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tesis.app.persistence.DatalinkRepository;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl
        implements Stoppable {

    private static final long serialVersionUID = 1L;
    private final DatalinkRepository datalinkRepo;
    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    @Autowired
    public DataSourceBasedMultiTenantConnectionProviderImpl(DatalinkRepository datalinkRepo) {
        this.datalinkRepo = datalinkRepo;
        getDatalinks();
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSourceMap.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantId) {
        if (!dataSourceMap.containsKey(tenantId)) {
            addDatalink(datalinkRepo.findByTenantId(tenantId));
        }
        return dataSourceMap.get(tenantId);
    }

    private void getDatalinks() {
        Iterable<Datalink> datalinks = datalinkRepo.findAll();
        if (Iterables.isEmpty(datalinks)) {
            Datalink datalink = new Datalink();
            datalink.setTenantId("0");
            datalink.setName("DATABASE_URL");
            datalinkRepo.save(datalink);
            addDatalink(datalink);
        } else {        
            for (Datalink datalink : datalinks) {
                addDatalink(datalink);
            }
        }
    }

    private void addDatalink(Datalink datalink) {
        dataSourceMap.put(datalink.getTenantId(),
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
