package com.project.FlipKart.service;

import com.project.FlipKart.entities.OrderedItems;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface OrderedItemsServiceImpl {
    public List<OrderedItems> allOrderedItems();
    public void placeAnOrder(String username,String productName, int quantity) throws UserDefinedException;
}
