package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Order;
import com.project.FlipKart.entities.OrderedItem;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderServiceImpl.getOrders();
    }

    @GetMapping("/orders/{user-id}")
    public  List<Order> getOrders(@PathVariable("user-id")int userId) throws UserDefinedException {
        return orderServiceImpl.getOrders(userId);
    }

    @PostMapping("/order-items")
    public  String orderItem(@RequestBody List<OrderedItem> orderedItems, @RequestParam("user-id")int userId) throws UserDefinedException {
        orderServiceImpl.placeOrder(orderedItems, userId);
        return "Order placed successfully";
    }
    @DeleteMapping("/orders/{order-id}/userid/{user-id}")
    public String cancelOrder(@PathVariable("order-id")int orderId,@PathVariable("user-id")int userId)throws UserDefinedException{
        return orderServiceImpl.cancelOrder(orderId,userId);
    }

}
