package shenaj.shenajtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shenaj.shenajtask.domain.AppUser;
import shenaj.shenajtask.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users-management")
public class UsersManagement {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<AppUser> usersList() {
        return service.allUsers();
    }

    @DeleteMapping
    public void deleteUser(@RequestBody int id) {
        service.deleteUser(id);
    }

    @PutMapping
    public AppUser updateUser(@RequestBody AppUser appUser) {
        AppUser savedUser = service.save(appUser);
        return savedUser;
    }
}
