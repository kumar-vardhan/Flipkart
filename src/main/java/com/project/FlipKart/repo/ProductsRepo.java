package com.project.FlipKart.repo;

import com.project.FlipKart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepo extends JpaRepository<Product,Integer> {



    List<Product> findByCategory(String category);
}
