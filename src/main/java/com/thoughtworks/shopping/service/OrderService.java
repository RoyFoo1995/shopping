package com.thoughtworks.shopping.service;

import com.thoughtworks.shopping.entity.Order;
import com.thoughtworks.shopping.viewobject.OrderItemRequest;
import com.thoughtworks.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }


    public Long create(List<OrderItemRequest> orderItemRequests, Long userId) {
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setUserId(userId);
        Long orderId = orderRepository.save(order).getId();
        orderItemRequests.forEach(orderItemRequest -> orderItemService.create(orderItemRequest, orderId));
        return orderId;
    }

    public Order get(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        AtomicReference<Float> totalPrice = new AtomicReference<>((float) 0);
        if (order != null) {
            order.getOrderItems().forEach(orderItem ->
                    totalPrice.updateAndGet(v ->
                            ((float) (v + orderItem.getCount() *
                                    orderItem.getProduct().getPrice()))));
//            return order.getOrderItems().stream().reduce(0L,(price,orderItem) ->
//                price+=orderItem.getCount()*orderItem.getProduct().getPrice());
            order.setTotalPrice(totalPrice.get());
        }
        return order;
    }
}
