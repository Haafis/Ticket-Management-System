package com.ticket_management_system.controller;
import com.ticket_management_system.model.dao.CustomersDao;
import com.ticket_management_system.model.dto.*;
import com.ticket_management_system.repository.CustomersRepository;
import com.ticket_management_system.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private CustomersService customersService;



    @PostMapping("/addcustomerdetails")
    public ServerMessageDto addNewCustomer(@RequestBody CustomersDto customersDto) {

        ServerMessageDto serverMessageDto = new ServerMessageDto();
        try {
            serverMessageDto = customersService.saveCustomerComplaints(customersDto);
            return serverMessageDto;
        }catch (Exception e){
            serverMessageDto.setSuccessFlag(0);
            serverMessageDto.setMessage( "ERROR.");
            return serverMessageDto;

        }
    }

    @PostMapping("/getAllCustomertDetails")
    public PageList<CustomersDao> pageList(@RequestBody PagingRequest pagingRequest) {
        PageList<CustomersDao> pageList = customersService.getAllCustomerComplaintsList(pagingRequest);
        return pageList;
    }

    @GetMapping("/getCustomerDetailsByID")
    public CustomersDto getCustomerDetailsByID(@RequestParam("id") Integer id) {
        return customersService.getCustomerComplaintDetailsByID(id);
    }

    @DeleteMapping("/deletecustomerdetailsbyid")
    public ServerMessageDto deleteCustomerDetailsByID(@RequestParam("id") Integer id) {
        ServerMessageDto serverMessageDto = customersService.deleteCustomerComplaintDetailsByID(id);
        return serverMessageDto;
    }

}
