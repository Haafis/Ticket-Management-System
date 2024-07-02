package com.ticket_management_system.controller;

import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.TicketManageDao;
import com.ticket_management_system.model.dto.*;
import com.ticket_management_system.repository.AgentRepository;
import com.ticket_management_system.repository.CustomersRepository;
import com.ticket_management_system.repository.ManageTicketRepository;
import com.ticket_management_system.service.AgentService;
import com.ticket_management_system.service.CustomersService;
import com.ticket_management_system.service.ManageTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ManageTicketController {
    @Autowired
    private ManageTicketRepository manageTicketRepository;
    @Autowired
    private ManageTicketService manageTicketService;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private CustomersService customersService;

    @PostMapping("/addcomplaintstatus")
    public ResponseEntity<String> addcomplaintstatus(@RequestBody TicketManageDto ticketManageDto) {
        try {
            manageTicketService.saveComplaintStatus(ticketManageDto);
            return ResponseEntity.ok("Status added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/getAllMangedCompDetails")
    public PageList<TicketManageDao> pageList(@RequestBody PagingRequest pagingRequest) {
        PageList<TicketManageDao> pageList = manageTicketService.getAllMangedCompDetailsList(pagingRequest);
        return pageList;
    }

    @GetMapping("/getManageComplaintsByID")
    public TicketManageDto getManageComplaintsByID(@RequestParam("id") Integer id) {
        return manageTicketService.getManageComplaintsByID(id);
    }

    @DeleteMapping("/deleteComplanitStatusByID")
    public ServerMessageDto deleteComplanitStatusByID(@RequestParam("id") Integer id) {
        ServerMessageDto serverMessageDto = manageTicketService.deleteComplanitStatusByID(id);
        return serverMessageDto;
    }

    @GetMapping("/getcustomertDetailsbyid")
    public CustomersDto getcustomertDetailsbyid(@RequestParam("id") Integer id) {
        return customersService.getCustomerComplaintDetailsByID(id);
    }
}
