package com.project.FlipKart.service;

import com.project.FlipKart.entities.Users;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface UserService {
    public List<Users> allUsers() ;

    public Users addUser(Users users) throws UserDefinedException;

    public void removeUser(int user_id) throws UserDefinedException;

    public Users updateAddress(int user_id, String address) throws UserDefinedException;

    public Double checkWalletBalance(int userId) throws UserDefinedException;
}
