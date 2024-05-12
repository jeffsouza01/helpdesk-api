package com.porto.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.porto.helpdesk.domain.enums.Profile;
import com.porto.helpdesk.dtos.TechnicianDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "tb_technician")
public class Technician extends Person{
    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Ticket> tickets = new ArrayList<>();

    public Technician() {
        addProfiles(Profile.CLIENT);
    }

    public Technician(String id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.CLIENT);

    }

    public Technician(TechnicianDTO tech) {
        this.id = tech.getId();
        this.name = tech.getName();
        this.cpf = tech.getCpf();
        this.email = tech.getEmail();
        this.password = tech.getPassword();
        this.createdAt = tech.getCreatedAt();
        this.profiles = tech.getProfiles().stream().map(profile -> profile.getCod()).collect(Collectors.toSet());
        addProfiles(Profile.CLIENT);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
