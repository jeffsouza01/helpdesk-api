package com.porto.helpdesk.repositories;

import com.porto.helpdesk.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Ticket, Integer> {
}
