package com.ticket_management_system.service.Impl;

import com.ticket_management_system.converters.AgentConverters;
import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.UserDao;
import com.ticket_management_system.model.dto.*;
import com.ticket_management_system.repository.AgentRepository;
import com.ticket_management_system.repository.UserRepository;
import com.ticket_management_system.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AgentConverters agentConverters;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public String saveAgent(AgentDto agentDto) {
        if (agentDto.getId() == 0) {
            UserDao userDao = agentConverters.toUserDao(agentDto);
            userRepository.save(userDao);
            AgentDao agentDao = agentConverters.toAgentDao(agentDto);
            agentDao.setUserid(userDao.getUserid());
            agentRepository.save(agentDao);



        } else {
            Optional<AgentDao> optionalAgentDao = agentRepository.findById(agentDto.getId());
            if (optionalAgentDao.isPresent()) {
                AgentDao agentDao = optionalAgentDao.get();
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
                agentRepository.save(agentDao);
                Optional<UserDao> optionalUserDao = userRepository.findById(agentDto.getId());
                if (optionalUserDao.isPresent()) {
                    UserDao userDao1 = optionalUserDao.get();
                    userDao1.setUsername(agentDto.getUsername());
                    String encryptedPassword = bCryptPasswordEncoder.encode(agentDto.getPassword());
                    userDao1.setPassword(encryptedPassword);
                    userRepository.save(userDao1);
                }

            } else {
                return "Agent with ID " + agentDto.getId() + " not found.";
            }
        }
        return "Agent saved successfully.";
    }

    public PageList<AgentDao> getAllAgentList(PagingRequest pagingRequest) {
        try {
            int pageNumber = 0;
            if (pagingRequest.getStart() > 0) {
                pageNumber = pagingRequest.getStart() / pagingRequest.getLength();
            }
            Pageable pageable = PageRequest.of(pageNumber, pagingRequest.getLength());
            String searchValue = pagingRequest.getSearch().getValue();
            Page<AgentDao> pagedResult;
            pagedResult = agentRepository.findAll(pageable);
            List<AgentDao> AgentDaoList = pagedResult.getContent();

            List<AgentDao> filtered = AgentDaoList.stream().collect(Collectors.toList());
            PageList<AgentDao> pageList = new PageList<>(filtered);
            pageList.setRecordsFiltered((int) pagedResult.getTotalElements());
            pageList.setRecordsTotal((int) pagedResult.getTotalElements());
            pageList.setDraw(pagingRequest.getDraw());
            return pageList;

        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public AgentDto getAgentDetailsByID(Integer id) {
        AgentDao agentDao = agentRepository.getById(id);
        AgentDto agentDto = agentConverters.toAgentDto(agentDao);
        return agentDto;
    }

    @Override
    public ServerMessageDto deleteAgentDetailsByID(Integer id) {
        ServerMessageDto serverMessageDto = new ServerMessageDto();
        try {
            agentRepository.deleteById(id);
            serverMessageDto.setSuccessFlag(1);
        } catch (Exception e) {
            serverMessageDto.setSuccessFlag(0);
        }
        return serverMessageDto;
    }
}
