package com.porto.helpdesk.controllers;

import com.porto.helpdesk.domain.Ticket;
import com.porto.helpdesk.dtos.TicketDTO;
import com.porto.helpdesk.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
