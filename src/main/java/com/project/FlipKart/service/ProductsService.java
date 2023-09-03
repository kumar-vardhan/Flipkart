package com.project.FlipKart.service;

import com.project.FlipKart.entities.Products;
import com.project.FlipKart.repo.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepo productsRepo;

    public List<Products> allProducts(){
        return  productsRepo.findAll();
    }
}
