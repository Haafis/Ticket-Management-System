package com.ticket_management_system.repository;

import com.ticket_management_system.model.dao.AgentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AgentRepository extends JpaRepository<AgentDao, Integer> {
}