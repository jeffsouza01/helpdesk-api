package com.porto.helpdesk.repositories;

import com.porto.helpdesk.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
