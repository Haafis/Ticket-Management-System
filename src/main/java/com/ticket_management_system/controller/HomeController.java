package com.ticket_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/index")
    public String Dashboard() {
        return "/pages/index";
    }

    @RequestMapping("/PopAgent")
    public String Agent() {
        return "/pages/agent";
    }

    @GetMapping(value = "/PopCustomer", params = "userid")
    public String PopCustomer(@RequestParam("userid") Integer userid, Model model) {
    model.addAttribute("userid", userid);
    return "/pages/customer";
}
    @RequestMapping("/PopAdmin")
    public String Admin() {
        return "/pages/admin";
    }

    @RequestMapping("/login")
    public String login() {
        return "/pages/login";
    }

    @GetMapping(value = "/PopDashboard", params = "userid")
    public String PopDashboard(@RequestParam("userid") Integer userid, Model model) {
        model.addAttribute("userid", userid);
        return "/pages/Dashboard";
    }

    @GetMapping(value = "/PopTickets", params = "userid")
    public String PopTickets(@RequestParam("userid") Integer userid, Model model) {
        model.addAttribute("userid", userid);
        return "/pages/ticket";
    }

    @GetMapping(value = "/PopManageTickets")
    public String popManageTickets(@RequestParam("customerid") Integer customerid,
                                   @RequestParam("userID") Integer userID, Model model) {
        model.addAttribute("customerid", customerid);
        model.addAttribute("userID", userID);
        return "/pages/ManageTicket";
    }

    @GetMapping(value = "/PopCustomerTicketStatus", params = "Ticketid")
    public String PopCustomerTicketStatus(@RequestParam("Ticketid") Integer Ticketid, Model model) {
        model.addAttribute("Ticketid", Ticketid);
        return "/pages/CustomerTicketStatus";
    }



    @GetMapping(value = "/PopAgentDashBoad", params = "userid")
    public String PopAgentDashBoad(@RequestParam("userid") Integer userid, Model model) {
        model.addAttribute("userid", userid);
        return "/pages/AgentDashBoad";
    }
}
