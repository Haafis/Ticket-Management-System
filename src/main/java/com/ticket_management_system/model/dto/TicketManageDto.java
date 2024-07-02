package com.ticket_management_system.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class TicketManageDto {
    private Integer id;
    private Integer agentid;
    private Integer customerid ;
    private Date IssueSolvedDate;
    private String status;
    private String Description;
}
