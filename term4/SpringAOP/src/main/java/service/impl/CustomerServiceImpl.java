package service.impl;

import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	public void addCustomer(){
		System.out.println("addCustomer() is running ");
	}
	
	public String addCustomerReturnValue(){
		System.out.println("addCustomerReturnValue() is running ");
		return "abc";
	}
	
	public void addCustomerThrowException() throws Exception {
		System.out.println("addCustomerThrowException() is running ");
		throw new Exception("Generic Error");
	}
	
	public void addCustomerAround(String name){
		System.out.println("addCustomerAround() is running, args : " + name);
	}
}