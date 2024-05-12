package com.porto.helpdesk.services;

import com.porto.helpdesk.domain.Client;
import com.porto.helpdesk.domain.Person;
import com.porto.helpdesk.dtos.ClientDTO;
import com.porto.helpdesk.repositories.ClientRepository;
import com.porto.helpdesk.repositories.PersonRepository;
import com.porto.helpdesk.services.exceptions.DataIntegrityException;
import com.porto.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<ClientDTO> findAll(){
        List<Client> clients =  clientRepository.findAll();
        List<ClientDTO> clientsDTOs = clients.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());

        return clientsDTOs;
    }

    public Client findById(String id){
        Optional<Client> client1 = clientRepository.findById(id);
        return client1.orElseThrow(() -> new ObjectNotFoundException("Client not found! ID: " + id ));
    }

    public Client create(ClientDTO clientDTO) {
        clientDTO.setId(null);

        validateCPFAndEmail(clientDTO);

        Client newTech = new Client(clientDTO);
        return clientRepository.save(newTech);
    }

    public Client update(String id, ClientDTO clientDTO) {
        clientDTO.setId(id);

        Client oldObj = findById(id);
        validateCPFAndEmail(clientDTO);

        oldObj = new Client(clientDTO);
        return clientRepository.save(oldObj);
    }

    public void delete(String id) {
        Client client = findById(id);

        if (client.getTickets().size() > 0) {
            throw new DataIntegrityException("Client have tickets on demand and not possible be deleted");
        }

        clientRepository.deleteById(id);
    }

    private void validateCPFAndEmail(ClientDTO clientDTO) {

        Optional<Person> newTech = personRepository.findByCpf(clientDTO.getCpf());
        if (newTech.isPresent() && newTech.get().getId() != clientDTO.getId()) {
            throw new DataIntegrityException("CPF already exists!");
        }

        newTech = personRepository.findByEmail(clientDTO.getEmail());
        if (newTech.isPresent() && newTech.get().getId() != clientDTO.getId()) {
            throw new DataIntegrityException("Email already exists!");
        }
    }


}
