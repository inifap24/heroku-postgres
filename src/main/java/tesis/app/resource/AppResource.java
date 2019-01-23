/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.app.resource;

import java.awt.AWTEventMulticaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tesis.app.persistence.DatalinkRepository;
import tesis.app.transfer.DatalinkDTO;
import tesis.app.util.ObjectMapperUtils;

@RestController
@RequestMapping("/app")
public class AppResource {

    private final DatalinkRepository datalinkRepo;

    @Autowired
    public AppResource(DatalinkRepository datalinkRepo) {
        this.datalinkRepo = datalinkRepo;
    }

    @GetMapping
    public Iterable<DatalinkDTO> getAll() {
        Iterable<DatalinkDTO> datalinks = ObjectMapperUtils.mapAll(
                datalinkRepo.findAll(), DatalinkDTO.class
        );
        datalinks.forEach(d -> 
                d.setName(System.getenv(d.getName()))
        );
        return datalinks;
    }

}
