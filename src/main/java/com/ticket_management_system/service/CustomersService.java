package com.ticket_management_system.service;

import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.CustomersDao;
import com.ticket_management_system.model.dto.*;

import java.util.List;

public interface CustomersService {
//    String saveCustomerComplaints(CustomersDto customersDto);
   ServerMessageDto saveCustomerComplaints(CustomersDto customersDto);
    public PageList<CustomersDao> getAllCustomerComplaintsList(PagingRequest request);

    public CustomersDto getCustomerComplaintDetailsByID(Integer id);

    public ServerMessageDto deleteCustomerComplaintDetailsByID(Integer id);
    CustomerTicketStatusDto getCustomerComplaintStatusByID(Integer id);

}
