package com.ticket_management_system.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AgentDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;

    private String role;
    private Integer status;
    private String department;
    private Date dateofjoining;
    private String address;
    private String username;
    private String password;
    private Integer userid;
}
