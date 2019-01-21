package tesis.tenant.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.app.common.CrudService;
import tesis.app.common.impl.CrudResourceImpl;
import tesis.tenant.domain.ClimData;

@RestController
@RequestMapping("/climDataService")
public class ClimDataResourceImpl extends CrudResourceImpl<ClimData, String> {
    
    @Autowired
    public ClimDataResourceImpl(@Qualifier("ClimDataService") CrudService<ClimData, String> service) {
        super(service);
    }

}