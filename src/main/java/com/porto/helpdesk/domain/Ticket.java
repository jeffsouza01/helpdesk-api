package com.porto.helpdesk.domain;

import com.porto.helpdesk.domain.enums.Priority;
import com.porto.helpdesk.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "tb_ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime openAt = LocalDateTime.now();

    private LocalDateTime closedAt;

    @NotNull(message = "PRIORITY field is required")
    private Priority priority;

    private Status status = Status.OPEN;
    @NotNull(message = "Title field is required")
    private String title;
    @NotNull(message = "Description field is required")
    private String description;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Ticket() {
    }

    public Ticket(Integer id, Priority priority, String title, String description, Technician technician, Client client) {
        this.id = id;
        this.priority = priority;
        this.status = Status.OPEN;
        this.title = title;
        this.description = description;
        this.technician = technician;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOpenAt() {
        return openAt;
    }

    public void setOpenAt(LocalDateTime openAt) {
        this.openAt = openAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
