package com.porto.helpdesk.controllers;

import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.dtos.TechnicianDTO;
import com.porto.helpdesk.services.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/techs")
public class TechnicianController {

    @Autowired
    private TechnicianService service;

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        List<TechnicianDTO> results = service.findAll();
        return ResponseEntity.ok().body(results);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable String id){
        Technician techFound = service.findById(id);
        return  ResponseEntity.ok().body(new TechnicianDTO(techFound));

    }


    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO techDTO) {
        Technician newTech = service.create(techDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newTech.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable String id, @Valid @RequestBody TechnicianDTO techDTO){
        Technician techObj = service.update(id, techDTO);

        return ResponseEntity.ok().body(new TechnicianDTO(techObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
