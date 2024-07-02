package com.ticket_management_system.repository;

import com.ticket_management_system.model.dao.TicketManageDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ManageTicketRepository extends JpaRepository<TicketManageDao, Integer> {
}