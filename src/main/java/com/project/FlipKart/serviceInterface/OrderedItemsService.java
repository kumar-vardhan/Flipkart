package com.project.FlipKart.serviceInterface;

import com.project.FlipKart.entities.OrderedItem;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface OrderedItemsService {

    public List<OrderedItem> getOrderedItems();

}
