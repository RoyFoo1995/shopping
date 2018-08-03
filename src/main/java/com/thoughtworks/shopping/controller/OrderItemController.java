package com.thoughtworks.shopping.controller;

import com.thoughtworks.shopping.viewobject.OrderItemRequest;
import com.thoughtworks.shopping.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PutMapping("/{id}/orderItems")
    public ResponseEntity update(@PathVariable("id") Long orderId, @RequestBody OrderItemRequest orderItemRequest) {
        if (orderItemService.update(orderId, orderItemRequest)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();

    }

}
