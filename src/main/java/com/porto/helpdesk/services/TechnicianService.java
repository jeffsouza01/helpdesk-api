package com.porto.helpdesk.services;

import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.dtos.TechnicianDTO;
import com.porto.helpdesk.repositories.TechnicianRepository;
import com.porto.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public List<TechnicianDTO> findAll(){
        List<Technician> techs =  technicianRepository.findAll();
        List<TechnicianDTO> techsDTO = techs.stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());

        return techsDTO;
    }

    public Technician findById(String id){
        Optional<Technician> tech1 = technicianRepository.findById(id);
        return tech1.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id ));
    }
}
