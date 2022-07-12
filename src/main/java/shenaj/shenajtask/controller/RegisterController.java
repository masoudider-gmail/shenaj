package shenaj.shenajtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shenaj.shenajtask.domain.AppUser;
import shenaj.shenajtask.domain.UserModel;
import shenaj.shenajtask.service.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity saveAppUser(@RequestBody UserModel userModel) {

        AppUser appUser = new AppUser(
                userModel.getUserName(),
                userModel.getPassword(),
                "user");

        service.save(appUser);

        return new ResponseEntity(userModel, HttpStatus.CREATED);
    }

}
