package com.project.FlipKart.repo;

import com.project.FlipKart.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders,Integer> {
}
