package com.ticket_management_system.controller;

import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dto.AgentDto;
import com.ticket_management_system.model.dto.PageList;
import com.ticket_management_system.model.dto.PagingRequest;
import com.ticket_management_system.model.dto.ServerMessageDto;
import com.ticket_management_system.repository.AgentRepository;
import com.ticket_management_system.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgentController {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AgentService agentService;

    @PostMapping("/addagentdetails")
    public ResponseEntity<String> addNewAgent(@RequestBody AgentDto agentDto) {
        try {
            agentService.saveAgent(agentDto);
            return ResponseEntity.ok("Agent added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/getAllagentDetails")
    public PageList<AgentDao> pageList(@RequestBody PagingRequest pagingRequest) {
        PageList<AgentDao> pageList = agentService.getAllAgentList(pagingRequest);
        return pageList;
    }

    @GetMapping("/getAgentDetailsByID")
    public AgentDto getAgentDetailsByID(@RequestParam("id") Integer id) {
        return agentService.getAgentDetailsByID(id);
    }

    @DeleteMapping("/deleteAgentDetailsByID")
    public ServerMessageDto deleteAgentDetailsByID(@RequestParam("id") Integer id) {
        ServerMessageDto serverMessageDto = agentService.deleteAgentDetailsByID(id);
        return serverMessageDto;
    }
}
