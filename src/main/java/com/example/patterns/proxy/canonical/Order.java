package com.example.patterns.proxy.canonical;

import java.util.HashMap;
import java.util.Map;


public class Order {
    private final long userId;
    private final Map<String, Long> items = new HashMap<>();

    public Order(long userId) {
        this.userId = userId;
    }

    public void putItem(String code, long count) {
        items.put(code, count);
    }

    public long getUserId() {
        return userId;
    }

    public Map<String, Long> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{userId=" + userId + ", items=" + items + "}";
    }
}
