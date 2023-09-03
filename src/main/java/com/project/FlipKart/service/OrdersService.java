package com.project.FlipKart.service;

import com.project.FlipKart.entities.Orders;
import com.project.FlipKart.repo.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepo ordersRepo;

    public List<Orders> allOrders(){
        return  ordersRepo.findAll();
    }
}
