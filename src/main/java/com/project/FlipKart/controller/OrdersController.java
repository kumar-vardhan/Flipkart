package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Orders;
import com.project.FlipKart.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/alloders")
    public List<Orders> allorders(){
        return ordersService.allOrders();
    }
}
