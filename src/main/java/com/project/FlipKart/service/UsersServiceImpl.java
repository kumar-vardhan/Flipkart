package com.project.FlipKart.service;


import com.project.FlipKart.entities.Users;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsersServiceImpl implements UserService{

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public List<Users> allUsers(){
        return usersRepo.findAll();
    }

    @Override
    public Users addUser(Users users) throws UserDefinedException{
        Pattern pattern2=Pattern.compile("[6-9][0-9]{9}");
        Matcher matcher2 = pattern2.matcher(users.getPhoneNo());
       Users users1 = new Users();
       if(matcher2.matches()){
            users1.setPhoneNo(users.getPhoneNo());
       }
       else{
           throw new UserDefinedException("please enter the valid phone no");
        }
        users1.setUsername(users.getUsername());
        users1.setUserPassword(users.getUserPassword());
        users1.setAddress(users.getAddress());

        return usersRepo.save(users1);
    }

    @Override
    public void removeUser(int user_id) throws UserDefinedException {
        Optional<Users> user=usersRepo.findById(user_id);
        if(user.isEmpty())
            throw new UserDefinedException("invaild user id");
        usersRepo.deleteById(user_id);
    }

    @Override
    public Users updateAddress(int user_id, String address) throws UserDefinedException {

        Optional<Users> user=usersRepo.findById(user_id);
        if(user.isEmpty())
            throw new UserDefinedException("Invalid user id");
        Users user1=user.get();
        user1.setAddress(address);
        usersRepo.save(user1);

        return user1;
    }

    @Override
    public Double checkWalletBalance(int userId) throws UserDefinedException {
        Optional<Users> users = usersRepo.findById(userId);
        if(users.isEmpty()){
            throw new UserDefinedException("Invalid userId");
        }
        Users users1 = users.get();
        return users1.getWallet();
    }


}
