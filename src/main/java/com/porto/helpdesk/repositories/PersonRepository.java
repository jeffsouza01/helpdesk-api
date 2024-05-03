package com.porto.helpdesk.repositories;

import com.porto.helpdesk.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
