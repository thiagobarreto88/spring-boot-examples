package com.example.restservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Customer;

@RestController
public class CustomerController {

	private final AtomicLong counter = new AtomicLong();
	
	org.apache.log4j.Logger log = Logger.getLogger(CustomerController.class);
	
	@GetMapping("/customer/{creditCardNumber}")
	public Customer greeting(@PathVariable(value = "creditCardNumber") String creditCardNumber) {
		
		log.trace("Credit card number: " +  creditCardNumber);
		return new Customer(counter.incrementAndGet());
	}
}