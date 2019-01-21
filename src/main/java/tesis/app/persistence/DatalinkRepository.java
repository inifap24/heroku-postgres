package tesis.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesis.app.domain.Datalink;

@Repository
public interface DatalinkRepository extends CrudRepository<Datalink, Long> {

    Datalink findByTenantId(@Param("tenantId") String tenantId);
    
}
