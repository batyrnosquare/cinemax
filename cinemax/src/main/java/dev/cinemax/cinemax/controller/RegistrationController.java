package dev.cinemax.cinemax.controller;


import dev.cinemax.cinemax.entity.User;
import dev.cinemax.cinemax.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    @PostMapping
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "User is saved";
    }

}
