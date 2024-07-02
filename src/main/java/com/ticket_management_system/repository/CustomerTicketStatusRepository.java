//package com.ticket_management_system.repository;
//
//import com.ticket_management_system.model.dto.CustomerTicketStatusDto;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface CustomerTicketStatusRepository extends JpaRepository<CustomerTicketStatusDto, Integer> {
//
//    @Query(value = "SELECT cs.id AS Ticketid, CONCAT(cs.firstname, ' ', cs.lastname) AS CustomerName, " +
//            "cs.complaint_notes AS Complaint, tm.issue_solved_date AS Solveddate, " +
//            "CONCAT(ag.firstname, ' ', ag.lastname) AS AgentName, tm.description AS Issue, " +
//            "tm.status AS Status " +
//            "FROM customer cs " +
//            "INNER JOIN ticket_manage tm ON tm.customerid = cs.id " +
//            "INNER JOIN agent ag ON tm.agentid = ag.id " +
//            "WHERE cs.id = :id", nativeQuery = true)
//    List<Object[]> getCustomerTicketStatusdetails(@Param("id") Integer id);
//}
