package com.porto.helpdesk.domain;

import com.porto.helpdesk.domain.enums.Profile;
import com.porto.helpdesk.dtos.ClientDTO;
import com.porto.helpdesk.dtos.TechnicianDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "tb_client")
public class Client extends Person{

    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets = new ArrayList<>();

    public Client() {
        addProfiles(Profile.CLIENT);
    }

    public Client(String id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        this.tickets = tickets;
        addProfiles(Profile.CLIENT);

    }

    public Client(ClientDTO client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.createdAt = client.getCreatedAt();
        this.profiles = client.getProfiles().stream().map(profile -> profile.getCod()).collect(Collectors.toSet());
        addProfiles(Profile.CLIENT);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
