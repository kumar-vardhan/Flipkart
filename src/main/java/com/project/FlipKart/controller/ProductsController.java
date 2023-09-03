package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Products;
import com.project.FlipKart.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/allproducts")
    public List<Products> allProducts(){
        return productsService.allProducts();
    }




}
