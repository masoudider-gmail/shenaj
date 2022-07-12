package shenaj.shenajtask.service;

import shenaj.shenajtask.domain.AppUser;
import shenaj.shenajtask.domain.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    void deleteById(int id);

    Ticket saveTicket(Ticket ticket);

    List<Ticket> findByAppUser(AppUser appUser);
}
