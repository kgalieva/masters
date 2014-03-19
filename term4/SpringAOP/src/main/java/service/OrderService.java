package service;

public interface OrderService {

    String getOrder(String orderNumber);

    String addOrder(String orderNumber, Long amount);

}
