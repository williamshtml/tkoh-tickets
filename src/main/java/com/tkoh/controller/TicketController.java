package com.tkoh.controller;
import com.tkoh.model.Ticket;
import com.tkoh.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> listar(){
        return ticketService.listarTickets();
    }

    @PostMapping
    public Ticket crear(@RequestBody Ticket ticket){
        return ticketService.crearTicket(ticket);
    }
}
