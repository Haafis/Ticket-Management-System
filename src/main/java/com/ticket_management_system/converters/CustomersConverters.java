package com.ticket_management_system.converters;

import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.CustomersDao;
import com.ticket_management_system.model.dto.AgentDto;
import com.ticket_management_system.model.dto.CustomersDto;
import org.springframework.stereotype.Component;

@Component
public class CustomersConverters {
    public CustomersDao toCustomersDao(CustomersDto customersDto) {
        CustomersDao customersDao = new CustomersDao();
        customersDao.setId(customersDto.getId());
        customersDao.setFirstname(customersDto.getFirstname());
        customersDao.setLastname(customersDto.getLastname());
        customersDao.setEmail(customersDto.getEmail());
        customersDao.setPhonenumber(customersDto.getPhonenumber());
        customersDao.setComplaint_notes(customersDto.getComplaint_notes());
        return customersDao;
    }

    public CustomersDto toCustomersDto(CustomersDao customersDao) {
        CustomersDto customersDto = new CustomersDto();
        customersDto.setId(customersDao.getId());
        customersDto.setFirstname(customersDao.getFirstname());
        customersDto.setLastname(customersDao.getLastname());
        customersDto.setEmail(customersDao.getEmail());
        customersDto.setPhonenumber(customersDao.getPhonenumber());
        customersDto.setComplaint_notes(customersDao.getComplaint_notes());
        return customersDto;
    }
}
