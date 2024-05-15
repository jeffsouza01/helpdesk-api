package com.porto.helpdesk.services;

import com.porto.helpdesk.domain.Client;
import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.domain.Ticket;
import com.porto.helpdesk.domain.enums.Priority;
import com.porto.helpdesk.domain.enums.Status;
import com.porto.helpdesk.dtos.TicketDTO;
import com.porto.helpdesk.repositories.TicketsRepository;
import com.porto.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketsRepository repository;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private ClientService clientService;

    public Ticket findById(Integer id) {
        Optional<Ticket> ticket = repository.findById(id);
        return ticket.orElseThrow(() -> new ObjectNotFoundException("Ticket not found! ID: " + id ));
    }


    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket create(TicketDTO ticketDTO) {
        return repository.save(newTicket(ticketDTO));
    }

    public Ticket update(Integer id, TicketDTO ticketDTO){
        ticketDTO.setId(id);

        Ticket oldTicket = findById(id);

        oldTicket = newTicket(ticketDTO);

        return repository.save(oldTicket);
    }

    private Ticket newTicket(TicketDTO ticketDTO) {
        Technician technician = technicianService.findById(ticketDTO.getTechnician());
        Client client = clientService.findById(ticketDTO.getClient());

        Ticket ticket = new Ticket();

        if (ticketDTO.getId() != null) {
            ticket.setId(ticketDTO.getId());
        }

        if (ticketDTO.getStatus() != null){
            ticket.setStatus(Status.toEnum(ticketDTO.getStatus()));
        }

        if (ticketDTO.getStatus() != null && ticketDTO.getStatus().equals(2)){
            ticket.setClosedAt(LocalDateTime.now());
        }

        ticket.setPriority(Priority.toEnum(ticketDTO.getPriority()));

        ticket.setTechnician(technician);
        ticket.setClient(client);
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setTitle(ticketDTO.getTitle());



        return ticket;
    }
}
