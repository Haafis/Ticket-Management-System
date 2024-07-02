package com.ticket_management_system.converters;

import com.ticket_management_system.model.dao.TicketManageDao;
import com.ticket_management_system.model.dto.TicketManageDto;
import org.springframework.stereotype.Component;

@Component
public class ManageTicketConverters {
    public TicketManageDao toTicketManageDao(TicketManageDto ticketManageDto) {
        TicketManageDao ticketManageDao = new TicketManageDao();
        ticketManageDao.setId(ticketManageDto.getId());
        ticketManageDao.setAgentid(ticketManageDto.getAgentid());
        ticketManageDao.setCustomerid(ticketManageDto.getCustomerid());
        ticketManageDao.setIssueSolvedDate(ticketManageDto.getIssueSolvedDate());
        ticketManageDao.setStatus(ticketManageDto.getStatus());
        ticketManageDao.setDescription(ticketManageDto.getDescription());
        return ticketManageDao;
    }

    public TicketManageDto toTicketManageDto(TicketManageDao ticketManageDao) {
        TicketManageDto ticketManageDto = new TicketManageDto();
        ticketManageDto.setId(ticketManageDao.getId());
        ticketManageDto.setAgentid(ticketManageDao.getAgentid());
        ticketManageDto.setCustomerid(ticketManageDao.getCustomerid());
        ticketManageDto.setIssueSolvedDate(ticketManageDao.getIssueSolvedDate());
        ticketManageDto.setStatus(ticketManageDao.getStatus());
        ticketManageDto.setDescription(ticketManageDao.getDescription());
        return ticketManageDto;
    }
}
