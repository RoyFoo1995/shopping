package com.thoughtworks.shopping.controller;

import com.thoughtworks.shopping.entity.Product;
import com.thoughtworks.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product newProduct = productService.create(product);
        return ResponseEntity.created(URI.create("/products" + newProduct.getId())).body(newProduct);
    }
}
