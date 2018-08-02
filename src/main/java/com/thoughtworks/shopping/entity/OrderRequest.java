package com.thoughtworks.shopping.entity;

public class OrderRequest {
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
