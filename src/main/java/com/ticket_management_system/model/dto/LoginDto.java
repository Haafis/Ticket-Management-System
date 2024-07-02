package com.ticket_management_system.model.dto;

import lombok.Data;

@Data
public class LoginDto {
    private  String loginUsername;
    private String loginPassword;
    private String loginRole;
}
