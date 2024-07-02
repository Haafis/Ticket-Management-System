package com.ticket_management_system.model.dto;

public class LoginResponse {
    private String role;
    private Integer userid;


    public LoginResponse(String role, Integer userid) {
        this.role = role;
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
