package com.project.FlipKart.service;


import com.project.FlipKart.entities.User;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.UsersRepo;
import com.project.FlipKart.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public List<User> getUsers(){
        return usersRepo.findAll();
    }

    @Override
    public User addUser(User user) throws UserDefinedException{
        Pattern pattern2=Pattern.compile("[6-9][0-9]{9}");
        Matcher matcher2 = pattern2.matcher(user.getPhoneNo());
       User user1 = new User();
       if(matcher2.matches()){
            user1.setPhoneNo(user.getPhoneNo());
       }
       else{
           throw new UserDefinedException("please enter the valid phone no");
        }
        user1.setUsername(user.getUsername());
        user1.setUserPassword(user.getUserPassword());
        user1.setAddress(user.getAddress());
        user1.setWallet(250000.00);
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());

        return usersRepo.save(user1);
    }

    @Override
    public void removeUser(int user_id) throws UserDefinedException {
        Optional<User> user=usersRepo.findById(user_id);
        if(user.isEmpty())
            throw new UserDefinedException("invaild user id");
        usersRepo.deleteById(user_id);
    }

    @Override
    public User updateAddress(int user_id, String address) throws UserDefinedException {

        Optional<User> user=usersRepo.findById(user_id);
        if(user.isEmpty())
            throw new UserDefinedException("Invalid user id");
        User user1=user.get();
        user1.setAddress(address);
        user1.setUpdatedAt(LocalDateTime.now());
        usersRepo.save(user1);

        return user1;
    }

    @Override
    public Double getWallet(int userId) throws UserDefinedException {
        Optional<User> users = usersRepo.findById(userId);
        if(users.isEmpty()){
            throw new UserDefinedException("Invalid userId");
        }
        User user1 = users.get();
        return user1.getWallet();
    }


}
