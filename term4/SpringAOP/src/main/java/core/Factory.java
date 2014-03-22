package core;

import service.impl.CustomerServiceImpl;
import service.impl.OrderServiceImpl;
import service.proxy.CustomerServiceProxy;

public class Factory {

    private static Factory factory = new Factory();

    private Factory() {}

    public static Factory getInstance() {
        return factory;
    }

    public Object getBean(String name) {
        if(name.equals("customerService")) {
            return new CustomerServiceProxy(new CustomerServiceImpl());
        }
        if(name.equals("orderService")) {
            return new OrderServiceImpl();
        }

        throw new IllegalArgumentException();
    }
}
