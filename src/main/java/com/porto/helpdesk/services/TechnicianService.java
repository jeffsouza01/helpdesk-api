package com.porto.helpdesk.services;

import com.porto.helpdesk.domain.Person;
import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.dtos.TechnicianDTO;
import com.porto.helpdesk.repositories.PersonRepository;
import com.porto.helpdesk.repositories.TechnicianRepository;
import com.porto.helpdesk.services.exceptions.DataIntegrityException;
import com.porto.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    public List<TechnicianDTO> findAll(){
        List<Technician> techs =  technicianRepository.findAll();
        List<TechnicianDTO> techsDTO = techs.stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());

        return techsDTO;
    }

    public Technician findById(String id){
        Optional<Technician> tech1 = technicianRepository.findById(id);
        return tech1.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id ));
    }

    public Technician create(TechnicianDTO techDTO) {
        techDTO.setId(null);
        techDTO.setPassword(encoder.encode(techDTO.getPassword()));

        validateCPFAndEmail(techDTO);

        Technician newTech = new Technician(techDTO);
        return technicianRepository.save(newTech);
    }

    public Technician update(String id, TechnicianDTO techDTO) {
        techDTO.setId(id);

        Technician oldObj = findById(id);
        validateCPFAndEmail(techDTO);

        oldObj = new Technician(techDTO);
        return technicianRepository.save(oldObj);
    }

    public void delete(String id) {
        Technician tech = findById(id);

        if (tech.getTickets().size() > 0) {
            throw new DataIntegrityException("Technician have tickets on demand and not possible be deleted");
        }

        technicianRepository.deleteById(id);
    }

    private void validateCPFAndEmail(TechnicianDTO techDTO) {

        Optional<Person> newTech = personRepository.findByCpf(techDTO.getCpf());
        if (newTech.isPresent() && newTech.get().getId() != techDTO.getId()) {
            throw new DataIntegrityException("CPF already exists!");
        }

        newTech = personRepository.findByEmail(techDTO.getEmail());
        if (newTech.isPresent() && newTech.get().getId() != techDTO.getId()) {
            throw new DataIntegrityException("Email already exists!");
        }
    }


}
