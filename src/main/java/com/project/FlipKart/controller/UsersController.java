package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Users;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @GetMapping("/allusers")
    public List<Users> allUsers(){
        return usersServiceImpl.allUsers();
    }

    @PostMapping("/adduser")
    public Users addUser(@RequestBody Users users) throws UserDefinedException{
        return  usersServiceImpl.addUser(users);
    }

    @DeleteMapping("/removeUser/{user_id}")
    public String removeUser(@PathVariable("user_id") int user_id) throws UserDefinedException {
        usersServiceImpl.removeUser(user_id);
        return "User "+user_id+" deleted";
    }


    @PutMapping("/updateAddress/{user_id}")
    public Users updateAddress(@PathVariable("user_id")int user_id ,@RequestParam("address") String address) throws UserDefinedException {
            return  usersServiceImpl.updateAddress(user_id,address);
    }

    @GetMapping("/checkWalletMoney/{userId}")
    public Double checkWallet(@PathVariable("userId") int userId)throws UserDefinedException{
        return usersServiceImpl.checkWalletBalance(userId);
    }

}
