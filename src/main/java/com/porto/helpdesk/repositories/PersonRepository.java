package com.porto.helpdesk.repositories;

import com.porto.helpdesk.domain.Person;
import com.porto.helpdesk.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByEmail(String email);
}
