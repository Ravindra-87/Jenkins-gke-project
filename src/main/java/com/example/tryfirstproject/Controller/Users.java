package com.example.tryfirstproject.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Users {

    @GetMapping("/getUsers")
    public String getUsers(){

        return "Ravi--------->Howard Roark---------->wynand";
    }
}
