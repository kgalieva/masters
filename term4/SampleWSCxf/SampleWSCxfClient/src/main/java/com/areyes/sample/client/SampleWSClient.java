package com.areyes.sample.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.service.SampleWebService;

public class SampleWSClient {

	
	public SampleWSClient() {
		
		ClassPathXmlApplicationContext classPathXmlAppContext = new ClassPathXmlApplicationContext("classpath:META-INF/beans.xml");
		classPathXmlAppContext.start();
		
		SampleWebService sampleWebService = (SampleWebService)classPathXmlAppContext.getBean("client");
		
		System.out.println(sampleWebService.getDataFromWebService().getName());
		System.out.println(sampleWebService.getDataFromWebService().getDescription());
		System.out.println(sampleWebService.getDataFromWebService().getAge());
		
	}
	
	public static void main(String[] args){
		new SampleWSClient();
	}
}
