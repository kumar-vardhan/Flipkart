package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Orders;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.service.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersServiceImpl ordersServiceImpl;

    @GetMapping("/alloders")
    public List<Orders> allorders(){
        return ordersServiceImpl.allOrders();
    }

    @GetMapping("/getOrders/{username}")
    public  List<Orders> getOrders(@PathVariable("username")String username) throws UserDefinedException {
        return ordersServiceImpl.getOrders(username);
    }
    @GetMapping("/cancelOrder/{orderId}/{userId}")
    public String cancelOrder(@PathVariable("orderId")int orderId,@PathVariable("userId")int userId)throws UserDefinedException{
        return ordersServiceImpl.cancelOrder(orderId,userId);
    }

}
