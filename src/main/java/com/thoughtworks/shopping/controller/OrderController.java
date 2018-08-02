package com.thoughtworks.shopping.controller;

import com.thoughtworks.shopping.entity.Order;
import com.thoughtworks.shopping.entity.OrderCreateRequest;
import com.thoughtworks.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody OrderCreateRequest orderCreateRequest) {
        Long orderId = orderService.create(orderCreateRequest.getOrderRequests(), orderCreateRequest.getUserId());
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable("id") Long id) {

        return ResponseEntity.ok(orderService.get(id));
    }
}
