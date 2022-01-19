package com.example.patterns.structural.proxy.modern;

import com.example.patterns.structural.proxy.canonical.Order;
import com.example.patterns.structural.proxy.canonical.RealOrderService;

import java.util.function.Consumer;


public class ModernProxyClient {
    public static void main(String[] args) {
        Order order = new Order(5L);
        order.putItem("XP", 2L);

        RealOrderService service = new RealOrderService();
        PermissionChecker checker = new PermissionChecker();
        Consumer<Order> processing = ((Consumer<Order>) checker::checkPermission)
                .andThen(service::processOrder);

        checker.setCurrentUser(5);
        processing.accept(order);
        checker.setCurrentUser(2);
        processing.accept(order);
    }
}
