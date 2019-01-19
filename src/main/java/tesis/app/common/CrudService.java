package tesis.app.common;

public interface CrudService<T, ID> {
    
    Iterable<T> getAll();
    T getById(ID id);
    T save(T entity);
    T update(ID id, T entity);
    void remove(ID id);
    Iterable<T> saveAll(Iterable<T> entities);
    void removeAll(Iterable<T> entities);
    
}
