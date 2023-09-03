package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Users;
import com.project.FlipKart.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/allusers")
    public List<Users> allUsers(){
        return usersService.allUsers();
    }

    @PostMapping("/adduser")
    public Users addUser(@RequestBody Users users){
        return  usersService.addUser(users);
    }

    @DeleteMapping("/removeUser/{user_id}")
    public String removeUser(@PathVariable("user_id") int user_id) {
        usersService.removeUser(user_id);
        return "User "+user_id+" deleted";
    }


    @PutMapping("/updateAddress/{user_id}")
    public Users updateAddress(@PathVariable("user_id")int user_id ,@RequestParam("address") String address){
            return  usersService.updateAddress(user_id,address);
    }

}
