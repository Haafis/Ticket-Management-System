package com.ticket_management_system.service;

import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.TicketManageDao;
import com.ticket_management_system.model.dto.*;

public interface ManageTicketService {
String saveComplaintStatus(TicketManageDto ticketManageDto);
    public PageList<TicketManageDao> getAllMangedCompDetailsList(PagingRequest request);

    public TicketManageDto getManageComplaintsByID(Integer id);

    public ServerMessageDto deleteComplanitStatusByID(Integer id);
}
