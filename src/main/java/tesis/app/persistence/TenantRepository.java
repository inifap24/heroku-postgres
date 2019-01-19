package tesis.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesis.app.domain.Tenant;

@Repository("TenantRepo")
public interface TenantRepository extends CrudRepository<Tenant, Long> {

    Tenant findByTenantId(@Param("tenantId") String tenantId);
    
}
