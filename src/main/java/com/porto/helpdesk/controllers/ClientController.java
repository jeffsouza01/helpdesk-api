package com.porto.helpdesk.controllers;

import com.porto.helpdesk.domain.Client;
import com.porto.helpdesk.dtos.ClientDTO;
import com.porto.helpdesk.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> results = service.findAll();
        return ResponseEntity.ok().body(results);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id){
        Client clientFound = service.findById(id);
        return  ResponseEntity.ok().body(new ClientDTO(clientFound));

    }


    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDTO) {
        Client newClient = service.create(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable String id, @Valid @RequestBody ClientDTO clientDTO){
        Client clientObj = service.update(id, clientDTO);

        return ResponseEntity.ok().body(new ClientDTO(clientObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
