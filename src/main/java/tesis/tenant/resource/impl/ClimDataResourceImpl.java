package tesis.tenant.resource.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.app.common.impl.CrudResourceImpl;
import tesis.tenant.domain.ClimData;
import tesis.tenant.hibernate.DataLinksProperties;
import tesis.tenant.persistence.ClimDataRepository;

@RestController
@RequestMapping("/climDataService")
public class ClimDataResourceImpl extends CrudResourceImpl<ClimData, String> {
    
    private final DataLinksProperties datalinksProps;
    
    @Autowired
    public ClimDataResourceImpl(
            @Qualifier("ClimDataRepository") CrudRepository<ClimData, String> repo, 
            DataLinksProperties datalinksProps) {
        super(repo);
        this.datalinksProps = datalinksProps;
    }
    
    @GetMapping("/getByYear/{year}")
    public Iterable<ClimData> getByYear(@PathVariable String year, @RequestHeader String tenantId) {
        return ((ClimDataRepository)repo).getByYear(year);
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