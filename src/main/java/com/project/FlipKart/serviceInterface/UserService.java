package com.project.FlipKart.serviceInterface;

import com.project.FlipKart.entities.User;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface UserService {
    public List<User> getUsers() ;

    public User addUser(User user) throws UserDefinedException;

    public void removeUser(int user_id) throws UserDefinedException;

    public User updateAddress(int user_id, String address) throws UserDefinedException;

    public Double getWallet(int userId) throws UserDefinedException;
}
