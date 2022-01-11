package com.example.patterns.proxy.canonical;


public class RealOrderService implements OrderService {
    @Override
    public void processOrder(Order order) {
        System.out.println("Order processed: " + order);
    }
}
