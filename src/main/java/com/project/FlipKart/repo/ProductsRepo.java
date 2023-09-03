package com.project.FlipKart.repo;

import com.project.FlipKart.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products,Integer> {

    Products findByProductName(String productName);
}
