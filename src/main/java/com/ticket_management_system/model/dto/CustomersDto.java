package com.ticket_management_system.model.dto;

import lombok.Data;

@Data
public class CustomersDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;


    private String complaint_notes;
}
