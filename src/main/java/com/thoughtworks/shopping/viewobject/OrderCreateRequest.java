package com.thoughtworks.shopping.viewobject;

import java.util.ArrayList;

public class OrderCreateRequest {
    private ArrayList<OrderItemRequest> orderItemRequests;
    private Long userId;

    public ArrayList<OrderItemRequest> getOrderItemRequests() {
        return orderItemRequests;
    }

    public Long getUserId() {
        return userId;
    }
}
