package dev.cinemax.cinemax.controller;

import dev.cinemax.cinemax.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("allUsers", userService.getUsers());
        return "admin";
    }

    @PostMapping
    public String deleteUser(@RequestParam(required = true, defaultValue = "") ObjectId id,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model){
        if (action.equals("delete")){
            userService.deleteUser(String.valueOf(id));
        }
        return "redirect:/admin";
    }

}
