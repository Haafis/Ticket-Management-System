package com.ticket_management_system.repository;

import com.ticket_management_system.model.dto.CustomerTicketStatusDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTicketStatusRepository extends JpaRepository<CustomerTicketStatusDto,Integer> {
    @Query(value = "SELECT " +
            "cs.id AS ticketid, " +
            "CONCAT(cs.firstname, ' ', cs.lastname) AS customername, " +
            "cs.complaint_notes AS complaint, " +
            "DATE_FORMAT(tm.issue_solved_date, '%d-%m-%Y') AS Solveddate, " +
            "CONCAT(ag.firstname, ' ', ag.lastname) AS agentname, " +
            "tm.description AS issue, " +
            "tm.status AS status " +
            "FROM customer cs " +
            "LEFT JOIN ticket_manage tm ON tm.customerid = cs.id " +
            "LEFT JOIN agent ag ON ag.id = tm.agentid " +
            "WHERE cs.id = :id", nativeQuery = true)
    CustomerTicketStatusDto getTicketInfoByCustomerId(@Param("id") Integer id);
}
