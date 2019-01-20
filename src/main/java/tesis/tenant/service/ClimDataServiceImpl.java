package tesis.tenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import tesis.app.common.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import tesis.tenant.domain.ClimData;
import tesis.tenant.persistence.ClimDataRepository;

@Service
public class ClimDataServiceImpl implements ClimDataService {

    public final ClimDataRepository repo;

    @Autowired
    public ClimDataServiceImpl(ClimDataRepository repo) {
        this.repo = repo;
    }
        
    @Override
    public Iterable<ClimData> getAll() {
        return repo.findAll();
    }

    @Override
    public ClimData getById(String id) {        
        return repo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClimData save(ClimData entity) {
        return repo.save(entity);
    }
    
    @Override
    public ClimData update(String id, ClimData entity) {
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);      
        return repo.save(entity);
    }

    @Override
    public Iterable<ClimData> saveAll(Iterable<ClimData> entities) {
        return repo.saveAll(entities);
    }

    @Override
    public void remove(String id) {
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        repo.deleteById(id);
    }

    @Override
    public void removeAll(@RequestBody Iterable<ClimData> entities) {
        repo.deleteAll(entities);
    }

}
