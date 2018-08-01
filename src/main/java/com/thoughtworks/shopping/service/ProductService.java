package com.thoughtworks.shopping.service;

import com.thoughtworks.shopping.entity.Product;
import com.thoughtworks.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        product.setId(id);
        return productRepository.existsById(id) ?
                productRepository.save(product) :
                null;
    }

    public List<Product> getAll(String order, int pageSize, int pageNum, int maxPrice, int minPrice, String category) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize,
                new Sort(order.equals("ASC") ?
                        Sort.Direction.ASC : Sort.Direction.DESC, "price"));
        List<Product> productList = productRepository.findAll(pageRequest).getContent();
        if (maxPrice != 0) {
            productList = productList.stream().filter(product -> product.getPrice() <= maxPrice).collect(Collectors.toList());
        }
        if (minPrice != 0) {
            productList = productList.stream().filter(product -> product.getPrice() >= minPrice).collect(Collectors.toList());
        }
        if (!category.isEmpty()) {
            productList = productList.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
        }
        return productList;
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
