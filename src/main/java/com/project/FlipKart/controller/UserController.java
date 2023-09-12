package com.project.FlipKart.controller;

import com.project.FlipKart.entities.User;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userServiceImpl.getUsers();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) throws UserDefinedException{
        return  userServiceImpl.addUser(user);
    }
    
    @DeleteMapping("/users/{user-id}")
    public String removeUser(@PathVariable("user-id") int userId) throws UserDefinedException {
        userServiceImpl.removeUser(userId);
        return "User "+userId+" deleted";
    }
    
    @PutMapping("/address/{user-id}")
    public User updateAddress(@PathVariable("user-id")int userId , @RequestParam("address") String address) throws UserDefinedException {
            return  userServiceImpl.updateAddress(userId,address);
    }

    @GetMapping("/wallets/{user-id}")
    public Double checkWallet(@PathVariable("user-id") int userId)throws UserDefinedException{
        return userServiceImpl.getWallet(userId);
    }

}
