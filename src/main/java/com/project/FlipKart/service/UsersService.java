package com.project.FlipKart.service;


import com.project.FlipKart.entities.OrderedItems;
import com.project.FlipKart.entities.Users;
import com.project.FlipKart.repo.UsersRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    public List<Users> allUsers(){
        return usersRepo.findAll();
    }

    public Users addUser(Users users){
       Users users1 = new Users();
      users1.setUsername(users.getUsername());
        users1.setUserPassword(users.getUserPassword());
        users1.setAddress(users.getAddress());
        users1.setPhoneNo(users.getPhoneNo());
        return usersRepo.save(users1);
    }

    public void removeUser(int user_id) {
        Optional<Users> user=usersRepo.findById(user_id);
        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No data found");
        usersRepo.deleteById(user_id);
    }

    public Users updateAddress(int user_id, String address) {

        Optional<Users> user=usersRepo.findById(user_id);
        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No data found");
        Users user1=user.get();
        user1.setAddress(address);
        usersRepo.save(user1);

        return user1;
    }


}
