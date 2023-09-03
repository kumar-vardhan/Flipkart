package com.project.FlipKart.repo;

import com.project.FlipKart.entities.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemsRepo extends JpaRepository<OrderedItems,Integer> {
}
