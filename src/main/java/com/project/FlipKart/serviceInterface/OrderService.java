package com.project.FlipKart.serviceInterface;

import com.project.FlipKart.entities.Order;
import com.project.FlipKart.entities.OrderedItem;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface OrderService {
    public List<Order> getOrders();
    public List<Order> getOrders(int userId) throws UserDefinedException;

    public String cancelOrder(int orderId, int userId)throws  UserDefinedException;

    public void placeOrder(List<OrderedItem> orderedItems, int userId) throws UserDefinedException;

}
