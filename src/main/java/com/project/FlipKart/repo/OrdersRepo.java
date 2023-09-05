package com.project.FlipKart.repo;

import com.project.FlipKart.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders,Integer> {
    List<Orders> getByUserId(int userId);
}
