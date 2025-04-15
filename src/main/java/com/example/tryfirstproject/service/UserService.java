package com.example.tryfirstproject.service;

import com.example.tryfirstproject.entity.UsersEntity;
import com.example.tryfirstproject.model.Users;
import com.example.tryfirstproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    private List<Users> userList = new ArrayList<>();

    // Method to save a user
    public Users saveUser(Users user) {

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUsername(user.getName());
        userEntity.setUseremail(user.getEmail());

        userEntity=usersRepository.save(userEntity);

        Users users= new Users();
        users.setName(userEntity.getUsername());
        users.setEmail(userEntity.getUseremail());

        return users;
    }

    // Method to get all users
    public List<Users> getAllUsers() {
        return userList;
    }
}
