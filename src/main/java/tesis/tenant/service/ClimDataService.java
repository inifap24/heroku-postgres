package tesis.tenant.service;

import tesis.tenant.domain.ClimData;

public interface ClimDataService {
    
    Iterable<ClimData> getAll();
    ClimData getById(String id);
    ClimData save(ClimData entity);
    ClimData update(String id, ClimData entity);
    void remove(String id);
    Iterable<ClimData> saveAll(Iterable<ClimData> entities);
    void removeAll(Iterable<ClimData> entities);
    
}
