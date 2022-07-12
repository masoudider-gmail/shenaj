package shenaj.shenajtask.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shenaj.shenajtask.domain.AppUser;
import shenaj.shenajtask.domain.Ticket;
import shenaj.shenajtask.repository.TicketRepository;
import shenaj.shenajtask.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImp implements TicketService {

    @Autowired
    private TicketRepository repository;

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public List<Ticket> findByAppUser(AppUser appUser) {
        return repository.findAllByAppUser(appUser);
    }
}
