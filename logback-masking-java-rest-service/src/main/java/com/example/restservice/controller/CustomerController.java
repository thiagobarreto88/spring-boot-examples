package com.example.restservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Customer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/customer/{creditCardNumber}")
	public Customer greeting(@PathVariable(value = "creditCardNumber") String creditCardNumber) {
		
		log.trace("Credit card number: {}",  creditCardNumber);
		return new Customer(counter.incrementAndGet());
	}
}