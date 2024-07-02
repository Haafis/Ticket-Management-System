//package com.ticket_management_system.controller;
//
//import com.ticket_management_system.model.dto.CustomerTicketStatusDto;
//import com.ticket_management_system.model.dto.LoginDto;
//import com.ticket_management_system.model.dto.LoginResponse;
//import com.ticket_management_system.repository.CustomerTicketStatusRepository;
//import com.ticket_management_system.repository.UserRepository;
//import com.ticket_management_system.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class CustomerTicketStatusController {
//
//    @Autowired
//    private CustomerTicketStatusRepository customerTicketStatusRepository;
//
//    @PostMapping("/getCustomerTicketStatusdetails")
//    public ResponseEntity<?> getCustomerTicketStatusdetails(@RequestBody Map<String, Object> request) {
//        try {
//            Integer id = (Integer) request.get("id");
//            List<Object[]> customerTickets = customerTicketStatusRepository.getCustomerTicketStatusdetails(id);
//
//            if (!customerTickets.isEmpty()) {
//                List<CustomerTicketStatusDto> customerTicketStatusDtos = new ArrayList<>();
//                for (Object[] ticket : customerTickets) {
//                    CustomerTicketStatusDto dto = new CustomerTicketStatusDto(
//                            (Integer) ticket[0],
//                            (String) ticket[1],
//                            (String) ticket[2],
//                            (Date) ticket[3],
//                            (String) ticket[4],
//                            (String) ticket[5],
//                            (String) ticket[6]
//                    );
//                    customerTicketStatusDtos.add(dto);
//                }
//                return ResponseEntity.ok().body(Map.of("data", customerTicketStatusDtos));
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tickets found.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//}
