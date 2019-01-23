/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.app.resource;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.app.persistence.DatalinkRepository;

@RestController
@RequestMapping("/app")
public class AppResource {
    
    private final DatalinkRepository datalinkRepo;

    @Autowired
    public AppResource(DatalinkRepository datalinkRepo) {
        this.datalinkRepo = datalinkRepo;
    }        
    
    @GetMapping
    public Map<String, String> getAll() {
        Map<String, String> datalinks = new HashMap<>();
        datalinkRepo.findAll().forEach(
                d -> datalinks.put(d.getTenantId(), System.getenv(d.getName()))
        );
        return datalinks;
    }
    
}
