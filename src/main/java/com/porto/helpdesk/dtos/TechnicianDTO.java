package com.porto.helpdesk.dtos;

import com.porto.helpdesk.domain.Technician;
import com.porto.helpdesk.domain.enums.Profile;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianDTO {
    protected String id;
    @NotNull(message = "Field NAME is required")
    protected String name;

    @NotNull(message = "Field CPF is required")
    protected String cpf;
    @NotNull(message = "Field EMAIL is required")
    protected String email;

    @NotNull(message = "Field PASSWORD is required")
    protected String password;

    protected Set<Integer> profiles = new HashSet<>();

    protected LocalDateTime createdAt = LocalDateTime.now();

    public TechnicianDTO(){
        addProfiles(Profile.CLIENT);
    }

    public TechnicianDTO(Technician tech) {
        this.id = tech.getId();
        this.name = tech.getName();
        this.cpf = tech.getCpf();
        this.email = tech.getEmail();
        this.password = tech.getPassword();
        this.createdAt = tech.getCreatedAt();
        this.profiles = tech.getProfiles().stream().map(profile -> profile.getCod()).collect(Collectors.toSet());
        addProfiles(Profile.CLIENT);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfiles(Profile profiles) {
        this.profiles.add(profiles.getCod());
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
