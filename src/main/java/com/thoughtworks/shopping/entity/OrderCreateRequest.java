package com.thoughtworks.shopping.entity;

import java.util.ArrayList;
import java.util.Date;

public class OrderCreateRequest {
    private ArrayList<OrderRequest> orderRequests;
    private Long userId;

    public ArrayList<OrderRequest> getOrderRequests() {
        return orderRequests;
    }

    public Long getUserId() {
        return userId;
    }
}
