package tesis.tenant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import tesis.app.common.impl.CrudServiceImpl;
import tesis.tenant.service.ClimDataService;

@Service("ClimDataService")
public class ClimDataServiceImpl<ClimData, String> extends CrudServiceImpl implements ClimDataService {    

    @Autowired
    public ClimDataServiceImpl(@Qualifier("ClimDataRepository") CrudRepository<ClimData, String> repo) {
        super(repo);
    }  
        
}
