package tesis.tenant.resource.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.app.common.CrudService;
import tesis.app.common.impl.CrudResourceImpl;
import tesis.tenant.domain.ClimData;
import tesis.tenant.hibernate.DataLinksProperties;

@RestController
@RequestMapping("/climDataService")
public class ClimDataResourceImpl extends CrudResourceImpl<ClimData, String> {
    
    private final DataLinksProperties datalinksProps;
    
    @Autowired
    public ClimDataResourceImpl(
            @Qualifier("ClimDataService") CrudService<ClimData, String> service, 
            DataLinksProperties datalinksProps) {
        super(service);
        this.datalinksProps = datalinksProps;
    }
    
    @GetMapping("/getDatalinks")
    public List<String> getDatalinks() {
        List<String> datalinks = new ArrayList<>();
        datalinksProps.getDatalinks().forEach(
                (k, v) -> datalinks.add(System.getenv(v))
        );
        return datalinks;
    }

}