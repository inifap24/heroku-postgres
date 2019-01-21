package tesis.app.common.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import tesis.app.common.CrudService;

public abstract class CrudResourceImpl<T, ID> {

    public final CrudService<T, ID> service;

    public CrudResourceImpl(CrudService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<T> getAll(@RequestHeader String tenantId) {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable ID id, @RequestHeader String tenantId) {
        return service.getById(id);
    }    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T save(@RequestBody T entity, @RequestHeader String tenantId) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity, @RequestHeader String tenantId) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable ID id, @RequestHeader String tenantId) {        
        service.remove(id);
    }

    @PostMapping("/saveAll")
    public Iterable<T> saveAll(@RequestBody Iterable<T> entities, @RequestHeader String tenantId) {
        return service.saveAll(entities);
    }

    @DeleteMapping("/removeAll")
    public void removeAll(@RequestBody Iterable<T> entities, @RequestHeader String tenantId) {
        service.removeAll(entities);
    }

}
