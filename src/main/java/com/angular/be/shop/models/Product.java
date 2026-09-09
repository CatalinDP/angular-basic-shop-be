package com.angular.be.shop.models;

import java.math.BigDecimal;
import java.util.Map;

public record Product(Long id, String name, BigDecimal price, String imageUrl, String description, Tag tag, Category category, Double rating, Map<String, String> specifications) {
    public Product(Long id, String name, BigDecimal price, String imageUrl, String description, Double rating) {
        this(id, name, price, imageUrl, description, null, null, rating, null);
    }
    public Product(Long id, String name, BigDecimal price, String imageUrl, String description, Tag tag, Double rating) {
        this(id, name, price, imageUrl, description, tag, null, rating, null);
    }
    public Product(Long id, String name, BigDecimal price, String imageUrl, String description, Category category, Double rating) {
        this(id, name, price, imageUrl, description, null, category, rating, null);
    }
}
