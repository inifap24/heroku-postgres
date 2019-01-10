package com.example.resource;

import com.example.domain.ClimData;
import com.example.service.ClimDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/climDataService")
public class ClimDataResource {
    
    private final ClimDataService service;

    @Autowired
    public ClimDataResource(ClimDataService service) {
        this.service = service;
    }
    
    @GetMapping
    public Iterable<ClimData> getAll() {
        return service.getAll();
    }
    
}
