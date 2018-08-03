package com.thoughtworks.shopping.service;

import com.thoughtworks.shopping.entity.Order;
import com.thoughtworks.shopping.entity.OrderItem;
import com.thoughtworks.shopping.viewobject.OrderItemRequest;
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

    public void create(OrderItemRequest orderItemRequest, Long orderId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setCount(orderItemRequest.getCount());
        orderItem.setProduct(productService.get(orderItemRequest.getProductId()));
        orderItemRepository.save(orderItem);
    }

    public boolean update(Long orderId, OrderItemRequest orderItemRequest) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return false;
        }
        AtomicBoolean noOrderItem = new AtomicBoolean(true);
        updateOrderItem(orderItemRequest, order, noOrderItem);
        if (noOrderItem.get() && orderItemRequest.getCount() > 0) {
            this.create(orderItemRequest, orderId);
        }
        return true;
    }

    private void updateOrderItem(OrderItemRequest orderItemRequest, Order order, AtomicBoolean noOrderItem) {
        order.getOrderItems().forEach(orderItem -> {
            if (orderItem.getProduct().getId().equals(orderItemRequest.getProductId())) {
                int count = orderItem.getCount() + orderItemRequest.getCount();
                orderItem.setCount(count);
                orderItemRepository.save(orderItem);
                noOrderItem.set(false);
                if (orderItem.getCount() <= 0) {
                    this.remove(orderItem.getId());
                }
            }
        });
    }

    private void remove(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
