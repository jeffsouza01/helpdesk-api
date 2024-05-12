package com.porto.helpdesk.services;

import com.porto.helpdesk.domain.Client;
import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.domain.Ticket;
import com.porto.helpdesk.domain.enums.Priority;
import com.porto.helpdesk.domain.enums.Profile;
import com.porto.helpdesk.domain.enums.Status;
import com.porto.helpdesk.repositories.ClientRepository;
import com.porto.helpdesk.repositories.TechnicianRepository;
import com.porto.helpdesk.repositories.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    public void initializeDB(){
        Technician tech1 = new Technician(null, "Valdir Costa", "38787484005", "valdir@mail.com", "123");
        tech1.addProfiles(Profile.CLIENT);

        Client cli1 = new Client(null, "Linus Torvalds", "02905363088", "torvals@mail.com", "123");

        Ticket tick1 = new Ticket(null, Priority.MEDIUM, Status.OPEN, "Ticket One", "First Ticket", tech1, cli1);


        clientRepository.saveAll(Arrays.asList(cli1));
        technicianRepository.saveAll(Arrays.asList(tech1));
        ticketsRepository.saveAll(Arrays.asList(tick1));

    }
}
