package com.example.tryfirstproject.controller;

import com.example.tryfirstproject.entity.UsersEntity;
import com.example.tryfirstproject.model.Users;
import com.example.tryfirstproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    UserService userService;


    @GetMapping("/getUsers")
    public ModelAndView getUsers(Model model) {

        List<UsersEntity> userList = userService.getAllUsers();

        userList.stream().forEach(usersEntity -> System.out.print(usersEntity.getUser_id()+" ,"));

        List<Users> usersList=userList.stream().map(usersEntity -> {
            Users user = new Users();
            user.setName(usersEntity.getUsername());
            user.setEmail(usersEntity.getUseremail());
            return user;
             }).collect(Collectors.toList());

        // Return a ModelAndView with the Thymeleaf template and the users list
        ModelAndView modelAndView = new ModelAndView("all-users");  // This will resolve to 'all-users.html'
        modelAndView.addObject("usersList", usersList);  // Add user list to the model for rendering


        return modelAndView; // Return the Thymeleaf template "members.html"
    }


    @PostMapping("/saveUser")
    public Users saveUser(@RequestBody Users user) {

        logger.info("user data--> "+user.toString());
        return userService.saveUser(user);
    }


}
