package com.porto.helpdesk.dtos;

import com.porto.helpdesk.domain.Ticket;

import java.time.LocalDateTime;

public class TickteDTO {

    private Integer id;
    private LocalDateTime openAt = LocalDateTime.now();
    private LocalDateTime closedAt;
    private Integer priority;
    private Integer status;
    private String title;
    private String description;
    private String technician;
    private String client;
    private String technicianName;
    private String clientName;

    public TickteDTO() {
    }

    public TickteDTO(Ticket ticketObj) {
        this.id = ticketObj.getId();
        this.openAt = ticketObj.getOpenAt();
        this.closedAt = ticketObj.getClosedAt();
        this.priority = ticketObj.getPriority().getCod();
        this.status = ticketObj.getStatus().getCod();
        this.title = ticketObj.getTitle();
        this.description = ticketObj.getDescription();
        this.technician = ticketObj.getTechnician().getId();
        this.client = ticketObj.getClient().getId();
        this.technicianName = ticketObj.getTechnician().getName();
        this.clientName = ticketObj.getClient().getName();
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
