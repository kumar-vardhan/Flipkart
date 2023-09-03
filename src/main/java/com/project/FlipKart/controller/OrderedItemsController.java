package com.project.FlipKart.controller;

import com.project.FlipKart.entities.OrderedItems;
import com.project.FlipKart.service.OrderedItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderedItemsController {


    @Autowired
    private OrderedItemsService orderedItemsService;

    @GetMapping("/allordereditems")
    public List<OrderedItems> allOrderedItems(){
        return  orderedItemsService.allOrderedItems();
    }

    @PostMapping("/placeAnOrder")
    public String placeAnOrder(@RequestBody OrderedItems orderedItems){
        orderedItemsService.additem(orderedItems);
        return "order places successfully";
    }

    @PostMapping("/orderAnItem")
    public  String orderAnItem(@RequestParam("username")String username,@RequestParam("productName")String productName,@RequestParam("quantity")int quantity){
        orderedItemsService.placeAnOrder(username,productName,quantity);
        return "Order placed succesfully";
    }
}
