package com.porto.helpdesk.controllers;

import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/techs")
public class TechnicianController {

    @Autowired
    private TechnicianService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Technician> findById(@PathVariable String id){
        Technician techFound = service.findById(id);
        return  ResponseEntity.ok().body(techFound);

    }
}
