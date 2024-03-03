package dev.cinemax.cinemax.controller;

import dev.cinemax.cinemax.entity.User;
import dev.cinemax.cinemax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/usersnames")
    public ResponseEntity<List<User>> getAllUsernames(){
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }
}
