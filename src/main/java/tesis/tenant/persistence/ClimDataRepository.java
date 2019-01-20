package tesis.tenant.persistence;

import tesis.tenant.domain.ClimData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimDataRepository extends CrudRepository<ClimData, String> {
    
}
