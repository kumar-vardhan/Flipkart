package com.project.FlipKart.service;

import com.project.FlipKart.entities.Orders;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface OrderService {
    public List<Orders> allOrders();
    public List<Orders> getOrders(String usernanme) throws UserDefinedException;

    public String cancelOrder(int orderId, int userId)throws  UserDefinedException;
}
