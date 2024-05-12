package com.porto.helpdesk.controllers;

import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.dtos.TechnicianDTO;
import com.porto.helpdesk.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
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
    public ResponseEntity<TechnicianDTO> create(@RequestBody TechnicianDTO techDTO) {
        Technician newTech = service.create(techDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newTech.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
