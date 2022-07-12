package shenaj.shenajtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shenaj.shenajtask.domain.Ticket;
import shenaj.shenajtask.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket-management")
public class TicketManagement {

    @Autowired
    private TicketService service;

    @GetMapping
    public List<Ticket> ticketList() {
        return service.findAll();
    }

    @DeleteMapping
    public void deleteTicket(@RequestBody int id) {
        service.deleteById(id);
    }

    @PutMapping
    public Ticket updateTicket(@RequestBody Ticket ticket) {
        return service.saveTicket(ticket);
    }
}