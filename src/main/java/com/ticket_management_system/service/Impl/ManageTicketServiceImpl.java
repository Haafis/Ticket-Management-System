package com.ticket_management_system.service.Impl;

import com.ticket_management_system.converters.ManageTicketConverters;
import com.ticket_management_system.model.dao.AgentDao;
import com.ticket_management_system.model.dao.TicketManageDao;
import com.ticket_management_system.model.dto.*;
import com.ticket_management_system.repository.ManageTicketRepository;
import com.ticket_management_system.service.ManageTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManageTicketServiceImpl implements ManageTicketService {

    @Autowired
    private ManageTicketRepository manageTicketRepository;
    @Autowired
    private ManageTicketConverters manageTicketConverters;



//    public String saveComplaintStatus(TicketManageDto ticketManageDto) {
//        if (ticketManageDto.getId() == 0) {
//            TicketManageDao ticketManageDao = manageTicketConverters.toTicketManageDao(ticketManageDto);
//            manageTicketRepository.save(ticketManageDao);
//        } else {
//            Optional<TicketManageDao> optionalTicketManageDao = manageTicketRepository.findById(ticketManageDto.getId());
//            if (optionalTicketManageDao.isPresent()) {
//                TicketManageDao ticketManageDao = optionalTicketManageDao.get();
//                ticketManageDao.setIssueSolvedDate(ticketManageDto.getIssueSolvedDate());
//                ticketManageDao.setStatus(ticketManageDto.getStatus());
//                ticketManageDao.setDescription(ticketManageDto.getDescription());
//                manageTicketRepository.save(ticketManageDao);
//            } else {
//                return "ComplaintStatus with ID " + ticketManageDto.getId() + " not found.";
//            }
//        }
//        return "Complaint Status saved successfully.";
//    }


    @Override
    public String saveComplaintStatus(TicketManageDto ticketManageDto) {
        if (ticketManageDto.getId() == 0) {
            TicketManageDao ticketManageDao = manageTicketConverters.toTicketManageDao(ticketManageDto);
            manageTicketRepository.save(ticketManageDao);
        } else {
            Optional<TicketManageDao> optionalTicketManageDao = manageTicketRepository.findById(ticketManageDto.getId());
            if (optionalTicketManageDao.isPresent()) {
                TicketManageDao ticketManageDao = optionalTicketManageDao.get();
                ticketManageDao.setAgentid(ticketManageDto.getAgentid());
                ticketManageDao.setCustomerid(ticketManageDto.getCustomerid());
                ticketManageDao.setIssueSolvedDate(ticketManageDto.getIssueSolvedDate());
                ticketManageDao.setStatus(ticketManageDto.getStatus());
                ticketManageDao.setDescription(ticketManageDto.getDescription());
                manageTicketRepository.save(ticketManageDao);
            } else {
                return "ComplaintStatus with ID " + ticketManageDto.getId() + " not found.";
            }
        }
        return "Complaint Status Saved successfully.";
    }

    public PageList<TicketManageDao> getAllMangedCompDetailsList(PagingRequest pagingRequest) {
        try {
            int pageNumber = 0;
            if (pagingRequest.getStart() > 0) {
                pageNumber = pagingRequest.getStart() / pagingRequest.getLength();
            }
            Pageable pageable = PageRequest.of(pageNumber, pagingRequest.getLength());
            String searchValue = pagingRequest.getSearch().getValue();
            Page<TicketManageDao> pagedResult;
            pagedResult = manageTicketRepository.findAll(pageable);
            List<TicketManageDao> masterDAOList = pagedResult.getContent();

            List<TicketManageDao> filtered = masterDAOList.stream().collect(Collectors.toList());
            PageList<TicketManageDao> pageList = new PageList<>(filtered);
            pageList.setRecordsFiltered((int) pagedResult.getTotalElements());
            pageList.setRecordsTotal((int) pagedResult.getTotalElements());
            pageList.setDraw(pagingRequest.getDraw());
            return pageList;

        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public TicketManageDto getManageComplaintsByID(Integer id) {
        TicketManageDao ticketManageDao = manageTicketRepository.getById(id);
        TicketManageDto ticketManageDto = manageTicketConverters.toTicketManageDto(ticketManageDao);
        return ticketManageDto;
    }

    @Override
    public ServerMessageDto deleteComplanitStatusByID(Integer id) {
        ServerMessageDto serverMessageDto = new ServerMessageDto();
        try {
            manageTicketRepository.deleteById(id);
            serverMessageDto.setSuccessFlag(1);
        } catch (Exception e) {
            serverMessageDto.setSuccessFlag(0);
        }
        return serverMessageDto;
    }

}
