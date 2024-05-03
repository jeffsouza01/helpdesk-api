package com.porto.helpdesk.repositories;

import com.porto.helpdesk.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, String> {
}
