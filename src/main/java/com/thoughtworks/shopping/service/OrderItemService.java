package com.thoughtworks.shopping.service;

import com.thoughtworks.shopping.entity.OrderItem;
import com.thoughtworks.shopping.entity.OrderRequest;
import com.thoughtworks.shopping.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    public void create(OrderRequest orderRequest, Long orderId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setCount(orderRequest.getCount());
        orderItem.setProduct(productService.get(orderRequest.getProductId()));
        orderItemRepository.save(orderItem);
    }
}
