package com.porto.helpdesk.services;

import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public List<Technician> findAll(){
        return technicianRepository.findAll();
    }

    public Technician findById(String id){
        Optional<Technician> tech1 = technicianRepository.findById(id);
        return tech1.orElse(null);
    }
}
