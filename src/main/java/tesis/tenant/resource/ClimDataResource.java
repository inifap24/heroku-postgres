package tesis.tenant.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.app.common.CrudResourceImpl;
import tesis.app.common.CrudService;

@RestController
@RequestMapping("/climDataService")
public class ClimDataResource<ClimData, String> extends CrudResourceImpl {

    @Autowired
    public ClimDataResource(@Qualifier("ClimDataService") CrudService<ClimData, String> service) {
        super(service);
    }

}
