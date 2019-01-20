package tesis.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesis.app.domain.Database;

@Repository
public interface DatabaseRepository extends CrudRepository<Database, Long> {

    Database findByTenantId(@Param("tenantId") String tenantId);
    
}
