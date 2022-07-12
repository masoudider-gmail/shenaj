package shenaj.shenajtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shenaj.shenajtask.domain.AppUser;
import shenaj.shenajtask.domain.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByAppUser(AppUser appUser);
}
