package com.porto.helpdesk.controllers;

import com.porto.helpdesk.domain.Ticket;
import com.porto.helpdesk.dtos.TicketDTO;
import com.porto.helpdesk.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable Integer id){
        Ticket ticket = ticketService.findById(id);

        return ResponseEntity.ok().body(new TicketDTO(ticket));
    }


    @GetMapping
    public  ResponseEntity<List<TicketDTO>> findAll(){
        List<Ticket> tickets = ticketService.findAll();
        List<TicketDTO> ticketDTOS = tickets.stream().map(obj -> new TicketDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(ticketDTOS);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketDTO ticketDTO){
        Ticket newTicket = ticketService.create(ticketDTO);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(newTicket.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> update(@PathVariable Integer id, @Valid @RequestBody TicketDTO tickDTO){
        Ticket updateTicket = ticketService.update(id, tickDTO);

        return ResponseEntity.ok().body(new TicketDTO(updateTicket));
    }
}
