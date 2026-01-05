package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.Customer;
import com.example.login.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer customer) {
        return service.login(customer.getEmail(), customer.getPassword());
    }

    @PostMapping("/register")
    public String register(@RequestBody Customer customer) {
        service.register(customer);
        return "Registered successfully";
    }
}


