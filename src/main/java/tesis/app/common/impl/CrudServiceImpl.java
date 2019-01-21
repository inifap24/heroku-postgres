package tesis.app.common.impl;

import tesis.app.common.exception.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

public abstract class CrudServiceImpl<T, ID> {

    public final CrudRepository<T, ID> repo;

    public CrudServiceImpl(CrudRepository<T, ID> repo) {
        this.repo = repo;
    }
        
    public Iterable<T> getAll() {
        return repo.findAll();
    }

    public T getById(ID id) {        
        return repo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public T save(T entity) {
        return repo.save(entity);
    }
    
    public T update(ID id, T entity) {
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);      
        return repo.save(entity);
    }

    public Iterable<T> saveAll(Iterable<T> entities) {
        return repo.saveAll(entities);
    }

    public void remove(ID id) {
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        repo.deleteById(id);
    }

    public void removeAll(Iterable<T> entities) {
        repo.deleteAll(entities);
    }

}
