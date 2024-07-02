package com.ticket_management_system.converters;

import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.UserDao;
import com.ticket_management_system.model.dto.AgentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AgentConverters {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public AgentDao toAgentDao(AgentDto agentDto) {
        AgentDao agentDao = new AgentDao();
        agentDao.setId(agentDto.getId());
        agentDao.setFirstname(agentDto.getFirstname());
        agentDao.setLastname(agentDto.getLastname());
        agentDao.setEmail(agentDto.getEmail());
        agentDao.setPhonenumber(agentDto.getPhonenumber());
        agentDao.setRole(agentDto.getRole());
        agentDao.setStatus(agentDto.getStatus());
        agentDao.setDepartment(agentDto.getDepartment());
        agentDao.setDateofjoining(agentDto.getDateofjoining());
        agentDao.setAddress(agentDto.getAddress());
        agentDao.setUsername(agentDto.getUsername());
        agentDao.setPassword(agentDto.getPassword());
        return agentDao;
    }

    public AgentDto toAgentDto(AgentDao agentDao) {
        AgentDto agentDto = new AgentDto();
        agentDto.setId(agentDao.getId());
        agentDto.setFirstname(agentDao.getFirstname());
        agentDto.setLastname(agentDao.getLastname());
        agentDto.setEmail(agentDao.getEmail());
        agentDto.setPhonenumber(agentDao.getPhonenumber());
        agentDto.setRole(agentDao.getRole());
        agentDto.setStatus(agentDao.getStatus());
        agentDto.setDepartment(agentDao.getDepartment());
        agentDto.setDateofjoining(agentDao.getDateofjoining());
        agentDto.setAddress(agentDao.getAddress());
        agentDto.setUsername(agentDao.getUsername());
        agentDto.setPassword(agentDao.getPassword());
        return agentDto;
    }

    public UserDao toUserDao(AgentDto agentDto) {
        UserDao userDao = new UserDao();
        userDao.setUsername(agentDto.getUsername());
        String encryptedPassword = bCryptPasswordEncoder.encode(agentDto.getPassword());
        userDao.setPassword(encryptedPassword);
        userDao.setRole("2");
        return userDao;
    }
}
