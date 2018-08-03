package com.thoughtworks.shopping.viewobject;

public class OrderItemRequest {
    private int count;
    private Long productId;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
