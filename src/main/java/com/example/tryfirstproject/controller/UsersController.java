package com.example.tryfirstproject.controller;


import com.example.tryfirstproject.entity.UsersEntity;
import com.example.tryfirstproject.model.Users;
import com.example.tryfirstproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class UsersController {


    @Autowired
    UserService userService;


    @GetMapping("/getUsers")
    public String getUsers(){

        return "Ravi------>Howard Roark----->wynand";
    }

    @PostMapping("/saveUser")
    public Users saveUser(@RequestBody Users user) {

        log.info("user data--> "+user.toString());
        userService.saveUser(user);
        return userService.saveUser(user);
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}
