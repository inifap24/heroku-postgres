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
import tesis.app.common.exception.ResourceNotFoundException;
import tesis.tenant.domain.ClimData;
import tesis.tenant.persistence.ClimDataRepository;

@RestController
@RequestMapping("/climDataService")
public class ClimDataResource {

    public final ClimDataRepository repo;

    @Autowired
    public ClimDataResource(ClimDataRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<ClimData> getAll(@RequestHeader String tenantId) {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ClimData getById(@PathVariable String id, @RequestHeader String tenantId) {
        return repo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClimData save(@RequestBody ClimData entity, @RequestHeader String tenantId) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public ClimData update(@PathVariable String id, @RequestBody ClimData entity, @RequestHeader String tenantId) {
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);      
        return repo.save(entity);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id, @RequestHeader String tenantId) {        
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        repo.deleteById(id);
    }

    @PostMapping("/saveAll")
    public Iterable<ClimData> saveAll(@RequestBody Iterable<ClimData> entities, @RequestHeader String tenantId) {
        return repo.saveAll(entities);
    }

    @DeleteMapping("/removeAll")
    public void removeAll(@RequestBody Iterable<ClimData> entities, @RequestHeader String tenantId) {
        repo.deleteAll(entities);
    }

}
