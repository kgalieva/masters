package com.sample.service;

import javax.jws.WebService;

import com.sample.generated.Person;

@WebService
public interface SampleWebService {
	public Person getDataFromWebService();
}
