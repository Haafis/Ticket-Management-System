package com.ticket_management_system.controller;

import com.ticket_management_system.model.dto.CustomerTicketStatusDto;
import com.ticket_management_system.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerTicketStatusController {

    @Autowired
    private CustomersService customerService;


    @GetMapping("/getCustomerTicketStatusdetails")
    public CustomerTicketStatusDto getCustomerTicketStatusdetails(@RequestParam("id") Integer id) {
        return customerService.getCustomerComplaintStatusByID(id);
    }

}
