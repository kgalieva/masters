package core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.CustomerService;

public class App {
	public static void main(String[] args) throws Exception {

		//ApplicationContext appContext =
       //         new ClassPathXmlApplicationContext("Spring-Customer.xml");
        Factory factory = Factory.getInstance();
		CustomerService customer = (CustomerService) factory.getBean("customerService");
		customer.addCustomer();
		
	/*	customer.addCustomerReturnValue();
		
		customer.addCustomerAround("customer#1");

        customer.addCustomerThrowException();*/

	}
}