package shenaj.shenajtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shenaj.shenajtask.domain.AppUser;
import shenaj.shenajtask.domain.Ticket;
import shenaj.shenajtask.service.TicketService;
import shenaj.shenajtask.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/user-profile")
public class UsersProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public AppUser getProfile(@AuthenticationPrincipal String user) {
        return appUser(user);
    }

    @PostMapping
    public Ticket saveTicket(@RequestBody String ticket, @AuthenticationPrincipal String user) {

        return ticketService.saveTicket(new Ticket(ticket, appUser(user)));
    }

    @GetMapping("/ticket")
    public List<Ticket> allTicket(@AuthenticationPrincipal String user) {

        return ticketService.findByAppUser(appUser(user));
    }

    private AppUser appUser(String user){
        return userService.findByUserName(user).get();
    }
}
