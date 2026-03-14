package com.tkoh.service;
import com.tkoh.model.Ticket;
import com.tkoh.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
 private final TicketRepository ticketRepository; 
 
 public TicketService(TicketRepository ticketRepository) {
     this.ticketRepository = ticketRepository;
 }
 public List<Ticket> listarTickets(){
       return ticketRepository.findAll();
 }
 public Ticket crearTicket(Ticket ticket){        
       return ticketRepository.save(ticket);   
 }
}
