package tesis.tenant.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tesis.tenant.domain.ClimData;
import tesis.tenant.service.ClimDataService;

@RestController
@RequestMapping("/climDataService")
public class ClimDataResource {

    public final ClimDataService service;

    @Autowired
    public ClimDataResource(ClimDataService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<ClimData> getAll(@RequestHeader String tenantId) {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClimData getById(@PathVariable String id, @RequestHeader String tenantId) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClimData save(@RequestBody ClimData entity, String tenantId) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ClimData update(@PathVariable String id, @RequestBody ClimData entity, @RequestHeader String tenantId) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id, @RequestHeader String tenantId) {        
        service.remove(id);
    }

    @PostMapping("/saveAll")
    public Iterable<ClimData> saveAll(Iterable<ClimData> entities, @RequestHeader String tenantId) {
        return service.saveAll(entities);
    }

    @DeleteMapping("/removeAll")
    public void removeAll(Iterable<ClimData> entities, @RequestHeader String tenantId) {
        service.removeAll(entities);
    }

}
