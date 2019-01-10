package com.example.service;

import com.example.domain.ClimData;
import com.example.persistence.ClimDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClimDataServiceImpl implements ClimDataService {
    
    private final ClimDataRepository repo;

    @Autowired
    public ClimDataServiceImpl(ClimDataRepository repo) {
        this.repo = repo;
    }
    
    @Override
    public Iterable<ClimData> getAll() {
        return repo.findAll();
    }
        
}
