package com.angular.be.shop.models;

public record CartItem(Product product, int quantity) {
    public CartItem withAddedQuantity(int extraQuantity) {
        return new CartItem(this.product, this.quantity + extraQuantity);
    }
}
