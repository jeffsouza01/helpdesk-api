package com.porto.helpdesk.services;

import com.porto.helpdesk.domain.Ticket;
import com.porto.helpdesk.repositories.TicketsRepository;
import com.porto.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketsRepository repository;

    public Ticket findById(Integer id) {
        Optional<Ticket> ticket = repository.findById(id);
        return ticket.orElseThrow(() -> new ObjectNotFoundException("Ticket not found! ID: " + id ));
    }


    public List<Ticket> findAll() {
        return repository.findAll();
    }
}
