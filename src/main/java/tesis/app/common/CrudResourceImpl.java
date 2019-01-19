package tesis.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class CrudResourceImpl<T, ID> {

    public final CrudService<T, ID> service;

    public CrudResourceImpl(CrudService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<T> getAll(@RequestHeader(value = "tenantId") String tenant) {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable ID id, @RequestHeader(value = "tenantId") String tenant) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T save(@RequestBody T entity, String tenant) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity, @RequestHeader(value = "tenantId") String tenant) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable ID id, @RequestHeader(value = "tenantId") String tenant) {        
        service.remove(id);
    }

    @PostMapping("/saveAll")
    public Iterable<T> saveAll(Iterable<T> entities, @RequestHeader(value = "tenantId") String tenant) {
        return service.saveAll(entities);
    }

    @DeleteMapping("/removeAll")
    public void removeAll(Iterable<T> entities, @RequestHeader(value = "tenantId") String tenant) {
        service.removeAll(entities);
    }

}
