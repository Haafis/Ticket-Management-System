package com.ticket_management_system.service.Impl;

import com.ticket_management_system.converters.AgentConverters;
import com.ticket_management_system.converters.CustomersConverters;
import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.CustomersDao;
import com.ticket_management_system.model.dto.*;
import com.ticket_management_system.repository.AgentRepository;
import com.ticket_management_system.repository.CustomersRepository;
import com.ticket_management_system.service.AgentService;
import com.ticket_management_system.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomersServiceImpl implements CustomersService {
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private CustomersConverters customersConverters;



//    public String saveCustomerComplaints(CustomersDto customersDto) {
//        if (customersDto.getId() == 0) {
//            CustomersDao customersDao = customersConverters.toCustomersDao(customersDto);
//            System.out.println("//++++++++++customersDao+++++++++++--"+customersDao);
//            customersRepository.save(customersDao);
//        } else {
//            Optional<CustomersDao> optionalCustomerDao = customersRepository.findById(customersDto.getId());
//            if (optionalCustomerDao.isPresent()) {
//                CustomersDao customersDao = optionalCustomerDao.get();
//                customersDao.setFirstname(customersDto.getFirstname());
//                customersDao.setLastname(customersDto.getLastname());
//                customersDao.setEmail(customersDto.getEmail());
//                customersDao.setPhonenumber(customersDto.getPhonenumber());
//                customersDao.setComplaint_notes(customersDto.getComplaint_notes());
//                customersRepository.save(customersDao);
//            } else {
//                return "Customer with ID " + customersDto.getId() + " not found.";
//            }
//        }
//        return "Agent saved successfully.";
//    }

    public ServerMessageDto saveCustomerComplaints(CustomersDto customersDto) {
        ServerMessageDto serverMessageDto = new ServerMessageDto();
        CustomersDao customersDao = new CustomersDao();
        if (customersDto.getId() == 0) {
             customersDao = customersConverters.toCustomersDao(customersDto);
            System.out.println("//++++++++++customersDao+++++++++++--"+customersDao);
            CustomersDao customersDaoNew = customersRepository.save(customersDao);
            serverMessageDto.setMasterID(customersDaoNew.getId());
        } else {
            Optional<CustomersDao> optionalCustomerDao = customersRepository.findById(customersDto.getId());
            if (optionalCustomerDao.isPresent()) {
                 customersDao = optionalCustomerDao.get();
                customersDao.setFirstname(customersDto.getFirstname());
                customersDao.setLastname(customersDto.getLastname());
                customersDao.setEmail(customersDto.getEmail());
                customersDao.setPhonenumber(customersDto.getPhonenumber());
                customersDao.setComplaint_notes(customersDto.getComplaint_notes());
                customersRepository.save(customersDao);
            } else {
                serverMessageDto.setSuccessFlag(0);
                serverMessageDto.setMessage( "Customer with ID " + customersDto.getId() + " not found.");
            return serverMessageDto;
            }
        }
        serverMessageDto.setSuccessFlag(1);
        serverMessageDto.setMessage( "Complaint Register Successfully.");
        return serverMessageDto;
    }

    public PageList<CustomersDao> getAllCustomerComplaintsList(PagingRequest pagingRequest) {
        try {
            int pageNumber = 0;
            if (pagingRequest.getStart() > 0) {
                pageNumber = pagingRequest.getStart() / pagingRequest.getLength();
            }
            Pageable pageable = PageRequest.of(pageNumber, pagingRequest.getLength());
            String searchValue = pagingRequest.getSearch().getValue();
            Page<CustomersDao> pagedResult;
            pagedResult = customersRepository.findAll(pageable);
            List<CustomersDao> masterDAOList = pagedResult.getContent();

            List<CustomersDao> filtered = masterDAOList.stream().collect(Collectors.toList());
            PageList<CustomersDao> pageList = new PageList<>(filtered);
            pageList.setRecordsFiltered((int) pagedResult.getTotalElements());
            pageList.setRecordsTotal((int) pagedResult.getTotalElements());
            pageList.setDraw(pagingRequest.getDraw());
            return pageList;

        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public CustomersDto getCustomerComplaintDetailsByID(Integer id) {
        CustomersDao customersDao = customersRepository.getById(id);
        CustomersDto customersDto = customersConverters.toCustomersDto(customersDao);
        return customersDto;
    }

    @Override
    public ServerMessageDto deleteCustomerComplaintDetailsByID(Integer id) {
        ServerMessageDto serverMessageDto = new ServerMessageDto();
        try {
            customersRepository.deleteById(id);
            serverMessageDto.setSuccessFlag(1);
        } catch (Exception e) {
            serverMessageDto.setSuccessFlag(0);
        }
        return serverMessageDto;
    }
}
