package service.proxy;

import service.CustomerService;

public class CustomerServiceProxy implements CustomerService {

    private CustomerService target;

    public CustomerServiceProxy(CustomerService customerService) {
        target = customerService;
    }

    @Override
    public void addCustomer() {
        System.out.println("logBefore() is running!");
        target.addCustomer();

    }

    @Override
    public String addCustomerReturnValue() {
        return null;
    }

    @Override
    public void addCustomerThrowException() throws Exception {

    }

    @Override
    public void addCustomerAround(String name) {

    }
}
