package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Products;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsServiceImpl productsServiceImpl;

    @GetMapping("/allproducts")
    public List<Products> allProducts(){
        return productsServiceImpl.allProducts();
    }

    @PostMapping("/addAnProduct")
    public  Products addAnProduct(@RequestBody Products products){
        return productsServiceImpl.addProduct(products);

    }

    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId) throws UserDefinedException {
        productsServiceImpl.deleteProduct(productId);
        return "Product "+productId+" deleted";
    }

    @PutMapping("/updateProduct/{price}")
    public Products updateAddress(@PathVariable("productId")int productId,@PathVariable("price")double price )throws UserDefinedException {
        return  productsServiceImpl.updatePrice(productId,price);
    }

    @GetMapping("/Products/{category}")
    public List<Products> getProductsByCategory(@PathVariable("category")String category) throws UserDefinedException{
        return productsServiceImpl.getProductsByCategory(category);
    }

}
