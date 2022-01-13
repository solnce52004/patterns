package com.example.patterns.proxy.canonical;


public class PermissionCheckingOrderServiceProxy implements OrderService {
    private final ThreadLocal<Long> currentUser = new ThreadLocal<>();
    private final OrderService delegate;

    public PermissionCheckingOrderServiceProxy(OrderService delegate) {
        this.delegate = delegate;
    }

    public void setCurrentUser(long userId) {
        currentUser.set(userId);
    }

    @Override
    public void processOrder(Order order) {
        if (order.getUserId() != currentUser.get()) {
//            throw new IllegalStateException("Order for another user can't be processed: " + order);
            System.out.println("getUserId: " + order.getUserId());
            System.out.println("currentUser: " + currentUser.get());
        }
        System.out.println("------");
        System.out.println("getUserId: " + order.getUserId());
        System.out.println("currentUser: " + currentUser.get());
        System.out.println("======");

        delegate.processOrder(order);
    }
}
