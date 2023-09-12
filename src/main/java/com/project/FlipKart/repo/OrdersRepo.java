package com.project.FlipKart.repo;

import com.project.FlipKart.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Order,Integer> {
    List<Order> getByUserId(int userId);
}
