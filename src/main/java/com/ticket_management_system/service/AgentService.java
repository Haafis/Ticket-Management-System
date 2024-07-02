package com.ticket_management_system.service;

import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dto.AgentDto;
import com.ticket_management_system.model.dto.PageList;
import com.ticket_management_system.model.dto.PagingRequest;
import com.ticket_management_system.model.dto.ServerMessageDto;


public interface AgentService {
    String saveAgent(AgentDto agentDto);
    public PageList<AgentDao> getAllAgentList(PagingRequest request);

    public AgentDto getAgentDetailsByID(Integer id);

    public ServerMessageDto deleteAgentDetailsByID(Integer id);
}