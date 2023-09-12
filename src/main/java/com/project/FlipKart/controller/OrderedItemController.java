package com.project.FlipKart.controller;

import com.project.FlipKart.entities.OrderedItem;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.OrderedItemsRepo;
import com.project.FlipKart.repo.OrdersRepo;
import com.project.FlipKart.service.OrderedItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderedItemController {


    @Autowired
    private OrderedItemServiceImpl orderedItemServiceImpl;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @GetMapping("/ordered-items")
    public List<OrderedItem> getOrderedItem(){
        return  orderedItemServiceImpl.getOrderedItems();
    }







}
