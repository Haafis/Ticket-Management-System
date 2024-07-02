# Ticket-Management-System
Ticket Management System

Introduction
The Ticket Management System is a web application designed to help organizations manage support tickets effectively. This system allows support agents to track and resolve customer issues, ensuring efficient customer service. The application is designed to be used by administrators, support agents, and customers.

System Overview
The system is divided into four main modules:
1.	Admin
2.	Agent
3.	Customer
4.	Tickets
The Admin module is responsible for managing agents, while agents can manage tickets and customer information. Customers can track and manage their own tickets.
•	To access this project, navigate to http://localhost:3999/index.
•	The Swagger UI for API documentation can be accessed at http://localhost:3999/swagger-ui/index.html#/.
•	The database connection string is jdbc:mysql://${MYSQL_HOST:localhost}:3313/ticketmanagementsystem.

Workflow
1.	Access the project at http://localhost:3999/index.
2.	On the right side, login or sign up as an administrator.
3.	After logging in as an administrator, you can add, edit, and delete agents.
4.	Logout from the admin module.
5.	Register a complaint and add customer details in the customer module. After saving the complaint, a ticket ID is generated.
6.	Login as an agent to see customer complaints and solve them in the agent module.
7.	After solving the complaint, the customer can search with the ticket ID to see the ticket status.
	
Database Design
The database consists of the following tables:
CREATE  TABLE `agent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dateofjoining` datetime(6) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);



CREATE TABLE `ticket_manage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `issue_solved_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `agentid` int DEFAULT NULL,
  `customerid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `complaint_notes` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



CREATE TABLE `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
);

API Design
The API follows RESTful principles and includes the following endpoints:
User Controller
•	POST /login
•	POST /SignUp
Agent Controller
•	POST /getAllagentDetails
•	POST /addagentdetails
•	GET /getAgentDetailsByID
•	DELETE /deleteAgentDetailsByID
Manage Ticket Controller
•	POST /getAllMangedCompDetails
•	POST /addcomplaintstatus
•	GET /getcustomertDetailsbyid
•	GET /getManageComplaintsByID
•	DELETE /deleteComplanitstatusByID
Customers Controller
•	POST /getAllcustomertDetails
•	POST /addcustomerdetails
•	GET /getCustomerDetailsByID
•	DELETE /deletecustomerdetailsbyid
Server Configuration
•	The application runs on port 3999.

