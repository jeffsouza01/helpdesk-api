package com.porto.helpdesk.controllers;

import com.porto.helpdesk.domain.Ticket;
import com.porto.helpdesk.dtos.TickteDTO;
import com.porto.helpdesk.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TickteDTO> findById(@PathVariable Integer id){
        Ticket ticket = ticketService.findById(id);

        return ResponseEntity.ok().body(new TickteDTO(ticket));
    }
}
