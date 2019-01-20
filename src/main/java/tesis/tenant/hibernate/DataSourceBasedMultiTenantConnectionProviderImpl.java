package tesis.tenant.hibernate;

import com.google.common.collect.Iterables;
import tesis.app.domain.Database;
import tesis.tenant.util.DataSourceUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.hibernate.service.spi.Stoppable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tesis.app.persistence.DatabaseRepository;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl
        implements Stoppable {

    private static final long serialVersionUID = 1L;
    private final DatabaseRepository databaseRepo;
    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    @Autowired
    public DataSourceBasedMultiTenantConnectionProviderImpl(DatabaseRepository databaseRepo) {
        this.databaseRepo = databaseRepo;
        getDatabases();
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSourceMap.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantId) {
        if (!dataSourceMap.containsKey(tenantId)) {
            addDatabase(databaseRepo.findByTenantId(tenantId));
        }
        return dataSourceMap.get(tenantId);
    }

    private void getDatabases() {
        Iterable<Database> databases = databaseRepo.findAll();
        if (Iterables.isEmpty(databases)) {
            Database database = new Database();
            database.setTenantId("860141");
            database.setName("HEROKU_POSTGRESQL_AMBER_URL");
            databaseRepo.save(database);
            addDatabase(database);
        } else {        
            for (Database database : databases) {
                addDatabase(database);
            }
        }
    }

    private void addDatabase(Database database) {
        dataSourceMap.put(database.getTenantId(),
                DataSourceUtil.createAndConfigureDataSource(database));
    }

    @Override
    public void stop() {
        if (dataSourceMap != null) {
            dataSourceMap.clear();
            dataSourceMap = null;
        }
    }

}
