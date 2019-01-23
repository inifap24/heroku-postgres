package tesis.app.common.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import tesis.app.common.exception.ResourceNotFoundException;

public abstract class CrudResourceImpl<T, ID> {

    public final CrudRepository<T, ID> repo;

    public CrudResourceImpl(CrudRepository<T, ID> repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<T> getAll(@RequestHeader String tenantId) {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable ID id, @RequestHeader String tenantId) {
        return repo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T save(@RequestBody T entity, @RequestHeader String tenantId) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity, @RequestHeader String tenantId) {
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);      
        return repo.save(entity);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable ID id, @RequestHeader String tenantId) {        
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        repo.deleteById(id);
    }

    @PostMapping("/saveAll")
    public Iterable<T> saveAll(@RequestBody Iterable<T> entities, @RequestHeader String tenantId) {
        return repo.saveAll(entities);
    }

    @DeleteMapping("/removeAll")
    public void removeAll(@RequestBody Iterable<T> entities, @RequestHeader String tenantId) {
        repo.deleteAll(entities);
    }

}
