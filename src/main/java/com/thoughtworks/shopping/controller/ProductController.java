package com.thoughtworks.shopping.controller;

import com.thoughtworks.shopping.entity.Product;
import com.thoughtworks.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

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

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable("id") Long id) {
        Product updateProduct = productService.update(id, product);
        return ResponseEntity.accepted().body(updateProduct);
    }

    //CHECKSTYLE.OFF: IllegalCatch Much more readable than catching 7 exceptions
    @GetMapping
    public ResponseEntity<List<Product>> getAll(
            @RequestParam(value = "order", defaultValue = "ASC") String order,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "maxPrice", defaultValue = "0") int maxPrice,
            @RequestParam(value = "minPrice", defaultValue = "0") int minPrice,
            @RequestParam(value = "category", defaultValue = "") String category) {
        List<Product> filterProducts = productService.getAll(order, pageSize, pageNum,
                maxPrice, minPrice, category);
        return ResponseEntity.ok(filterProducts);
    }
    //CHECKSTYLE.ON: IllegalCatch

    @GetMapping("{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Product product = productService.get(id);
        return product == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productService.get(id));
    }
}
