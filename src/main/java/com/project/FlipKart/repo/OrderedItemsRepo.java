package com.project.FlipKart.repo;

import com.project.FlipKart.entities.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemsRepo extends JpaRepository<OrderedItem,Integer> {
}
