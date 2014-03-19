package service.impl;

import service.OrderService;

public class OrderServiceImpl implements OrderService{
    @Override
    public String getOrder(String orderNumber) {
        throw new NullPointerException();
    }

    @Override
    public String addOrder(String orderNumber, Long amount) {
        System.out.println("Saving order " + orderNumber + " to database");
        return orderNumber;
    }
}
