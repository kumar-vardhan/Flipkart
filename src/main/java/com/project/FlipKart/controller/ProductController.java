package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Product;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productServiceImpl.getProducts();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productServiceImpl.addProduct(product);

    }

    @DeleteMapping("/products/{product-id}")
    public String deleteProduct(@PathVariable("product-id") int productId) throws UserDefinedException {
        productServiceImpl.deleteProduct(productId);
        return "Product "+productId+" deleted";
    }

    @PutMapping("/products/{price}")
    public Product updateProductPrice(@PathVariable("price")double price ,@RequestParam("product-id")int productId)throws UserDefinedException {
        return  productServiceImpl.updateProductPrice(productId,price);
    }

    @GetMapping("/products-by-category")
    public List<Product> getProductsByCategory(@RequestParam("category")String category) throws UserDefinedException{
        return productServiceImpl.getProductsByCategory(category);
    }

}
