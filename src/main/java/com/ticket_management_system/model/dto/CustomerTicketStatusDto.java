package com.ticket_management_system.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CustomerTicketStatusDto {
    @Id
    private Integer ticketid;
    private String customername;
    private String complaint;
    private String solveddate;
    private String agentname;
    private String issue;
    private String status;
}
