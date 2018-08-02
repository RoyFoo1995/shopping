package com.thoughtworks.shopping.service;

import com.thoughtworks.shopping.controller.OrderController;
import com.thoughtworks.shopping.entity.Order;
import com.thoughtworks.shopping.entity.OrderItem;
import com.thoughtworks.shopping.entity.OrderRequest;
import com.thoughtworks.shopping.repository.OrderItemRepository;
import com.thoughtworks.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    public void create(OrderRequest orderRequest, Long orderId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setCount(orderRequest.getCount());
        orderItem.setProduct(productService.get(orderRequest.getProductId()));
        orderItemRepository.save(orderItem);
    }

    public boolean update(Long orderId, OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return false;
        }
        AtomicBoolean noOrderItem = new AtomicBoolean(true);
        order.getOrderItems().forEach(orderItem -> {
            if (orderItem.getProduct().getId().equals(orderRequest.getProductId())) {
                int count = orderItem.getCount() + orderRequest.getCount();
                orderItem.setCount(count);
                orderItemRepository.save(orderItem);
                noOrderItem.set(false);
                if (orderItem.getCount() <= 0) {
                    this.remove(orderItem.getId());
                }
            }
        });

        if (noOrderItem.get() && orderRequest.getCount() > 0) {
            this.create(orderRequest, orderId);
        }
        return true;
    }

    private void remove(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
