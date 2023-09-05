package com.project.FlipKart.controller;

import com.project.FlipKart.entities.OrderedItems;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.OrderedItemsRepo;
import com.project.FlipKart.repo.OrdersRepo;
import com.project.FlipKart.service.OrderedItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderedItemsController {


    @Autowired
    private OrderedItemsService orderedItemsService;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @GetMapping("/allordereditems")
    public List<OrderedItems> allOrderedItems(){
        return  orderedItemsService.allOrderedItems();
    }



    @PostMapping("/orderAnItem")
    public  String orderAnItem(@RequestParam("username")String username,@RequestParam("productName")String productName,@RequestParam("quantity")int quantity) throws UserDefinedException {
        orderedItemsService.placeAnOrder(username,productName,quantity);
        return "Order placed succesfully";
    }



}
