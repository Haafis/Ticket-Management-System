package com.ticket_management_system.model.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "TicketManage")
public class TicketManageDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer agentid;
    private Integer customerid ;
    private Date IssueSolvedDate;
    private String status;
    private String Description;
}
